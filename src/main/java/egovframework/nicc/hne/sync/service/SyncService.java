package egovframework.nicc.hne.sync.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.model.entity.ComOrg;
import egovframework.nicc.hne.com.model.entity.ComStaff;
import egovframework.nicc.hne.com.model.entity.ComTeam;
import egovframework.nicc.hne.com.model.entity.ComTempOrg;
import egovframework.nicc.hne.com.model.entity.ComTempStaff;
import egovframework.nicc.hne.com.model.entity.ComTempTeam;
import egovframework.nicc.hne.com.model.entity.PotOrgDept;
import egovframework.nicc.hne.com.model.entity.PotOrgSection;
import egovframework.nicc.hne.com.model.entity.PotOrgUser;
import egovframework.nicc.hne.com.model.enumeration.Status;
import egovframework.nicc.hne.com.model.enumeration.Sync;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.com.repo.ComStaffRepo;
import egovframework.nicc.hne.com.repo.ComTeamRepo;
import egovframework.nicc.hne.com.repo.ComTempOrgRepo;
import egovframework.nicc.hne.com.repo.ComTempStaffRepo;
import egovframework.nicc.hne.com.repo.ComTempTeamRepo;
import egovframework.nicc.hne.com.repo.SyncOrgRepo;
import egovframework.nicc.hne.com.repo.SyncStaffRepo;
import egovframework.nicc.hne.com.repo.SyncTeamRepo;
import egovframework.nicc.hne.com.repo.impl.ComStaffRepoImpl;
import egovframework.nicc.hne.sync.model.dto.SyncScheduleDTO;
import egovframework.nicc.hne.sync.model.entity.SyncHistory;
import egovframework.nicc.hne.sync.model.entity.SyncSchedule;
import egovframework.nicc.hne.sync.repo.SaeolOrgRepo;
import egovframework.nicc.hne.sync.repo.SaeolStaffRepo;
import egovframework.nicc.hne.sync.repo.SyncHistoryRepo;
import egovframework.nicc.hne.sync.repo.SyncScheduleRepo;
import egovframework.nicc.hne.sync.repo.SyncUmsMapper;
import egovframework.nicc.hne.util.ModelConverter;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SyncService {

  @Autowired
  ComTempStaffRepo comTempStaffRepo;
  @Autowired
  ComStaffRepoImpl comStaffRepoImpl;
  @Autowired
  ComTempTeamRepo comTempTeamRepo;
  @Autowired
  ComTempOrgRepo comTempOrgRepo;

  @Autowired
  ComStaffRepo comStaffRepo;
  @Autowired
  ComTeamRepo comTeamRepo;
  @Autowired
  ComOrgRepo comOrgRepo;

  @Autowired
  SyncStaffRepo syncStaffRepo;
  @Autowired
  SyncTeamRepo syncTeamRepo;
  @Autowired
  SyncOrgRepo syncOrgRepo;

  @Autowired
  SyncUmsMapper syncUmsMapper;

  @Autowired
  ModelConverter modelConverter;

  @Autowired
  SyncScheduleRepo syncScheduleRepo;

  @Autowired
  SaeolOrgRepo saeolOrgRepo;
  @Autowired
  SaeolStaffRepo saeolStaffRepo;

  @Resource(name = "scheduler")
  TaskScheduler taskScheduler;

  @Autowired
  SyncHistoryRepo syncHistoryRepo;

  ScheduledFuture<?> future;
  // 스케쥴러 파트

  @Autowired
  VolService volService;

  public SyncScheduleDTO getSyncSchedule() {
    try {
      return modelConverter.map(syncScheduleRepo.findAll().get(0), SyncScheduleDTO.class);
    } catch (Exception e) {
      return null;
    }
  }

  public void updateSyncScheduler(SyncScheduleDTO syncScheduleDTO) {
    syncScheduleRepo.deleteAll();
    syncScheduleRepo.save(modelConverter.map(syncScheduleDTO, SyncSchedule.class));
  }

  public void changeCron() throws Exception {
    if (this.future != null) {
      this.future.cancel(true);
    }

    SyncSchedule syncSchedule = syncScheduleRepo.findAll().get(0);
    String cron = String.format("0 %s %s %s %s %s", syncSchedule.getScheduleMinute(), syncSchedule.getScheduleHour(),
        syncSchedule.getScheduleDay(), syncSchedule.getScheduleMonth(), syncSchedule.getScheduleWeek());

    log.info("set cron : {}", cron);

    final SyncHistory syncHistory = new SyncHistory();
    final ScheduledFuture<?> future = taskScheduler.schedule(new Runnable() {

      public void run() {
        try {
          if (syncScheduleRepo.findAll().isEmpty()) {
            // 중요코드!! 필수확인!!
            throw new InterruptedException("no schedule");
          }
          if (syncSchedule.getScheduleOrg()) {
            transToTempOrg("");
            transToComOrg();
            volService.updateClientVol(VolService.BUREAU);
          }
          if (syncSchedule.getScheduleTeam()) {
            transToTempTeam("");
            transToComTeam();
            volService.updateClientVol(VolService.BUREAU);
          }
          if (syncSchedule.getScheduleStaff()) {
            transToTempStaff("");
            transToComStaff();
            volService.updateClientVol(VolService.STAFF);
          }
          syncHistory.setHistorySuccess(true);
        } catch (Exception e) {
          syncHistory.setHistorySuccess(false);
          log.error("Error during scheduled task execution", e);
        } finally {
          syncHistoryRepo.save(syncHistory);
        }
      }
    }, new CronTrigger(cron));

    this.future = future;
  }

  public void resetCron() {
    if (future != null)
      future.cancel(true);
    new ThreadPoolTaskScheduler();
    syncScheduleRepo.deleteAll();
  }

  // ===================================CONVERT=====================================
  // Data => Temp
  // Temp => Entity 작업을 수행

  private ComTempStaff convertToTempStaff(PotOrgUser user) {
    // 순서 가공
    Integer staffSort = 0;
    try {
      staffSort = user.getOdr() == null ? 99 : Integer.parseInt(user.getOdr());
    } catch (Exception e) {
      staffSort = 99;
    }
    // 부서코드 가공
    String orgCd = null;
    try {
      orgCd = user.getDeptId().length() > 7 ? user.getDeptId().substring(0, 7) : user.getDeptId();
    } catch (Exception e) {
      orgCd = null;
    }
    ComTempStaff comTempStaff = ComTempStaff.builder() // 빌드
        .staffName(user.getUserName()) // 이름
        .staffPosition(user.getRankName()) // 직위
        .staffTelno(user.getTel()) // 전화번호
        .staffFaxno(null) // 팩스
        .staffBirth(user.getBirthDate()) // 생일
        .staffEmail(user.getMail()) // 메일
        .staffLogonId(user.getSaiallUserId()) // 아이디
        .staffSort(staffSort - 1) // 순서
        .staffDuty(user.getWorkDivisionContents()).orgCd(orgCd) // 부서코드
        .teamCd(user.getSectionId()) // 팀코드
        .staffStatus(Status.fromCode(user.getUserStatus())) // 상태
        .staffFilename(user.getImgUrl()) // 사진
        .staffGrade(user.getGradeName()) // 기구표 : 직급
        .staffGradeAppointDate(null) // 기구표 직급 임용일
        .staffUse(checkStaffUse(user)) // 삭제
        .build();
    return comTempStaff;
  }

  private static final Set<String> VALID_STATUSES = new HashSet<>(Arrays.asList(
      Status.AAA.name(), // 재직 
      Status.AAB.name() // 재직(파견)
  ));

  private boolean checkStaffUse(PotOrgUser user) {
    boolean isUsed = "1".equals(user.getUseYn());
    boolean isMarked = "Y".equals(user.getMarkYn());
    boolean isValidStatus = VALID_STATUSES.contains(user.getUserStatus());

    return isUsed && isMarked && isValidStatus;
  }

  private ComTempTeam convertToTempTeam(PotOrgSection section) {
    // 순서 가공
    Integer teamSort = 0;
    try {
      teamSort = section.getSectionOdr() == null ? 99 : Integer.parseInt(section.getSectionOdr());
    } catch (Exception e) {
      teamSort = 99;
    }
    // 부서코드 가공
    String orgCd = null;
    try {
      orgCd = section.getDeptId().length() > 7 ? section.getDeptId().substring(0, 7) : section.getDeptId();
    } catch (Exception e) {
      orgCd = null;
    }
    ComTempTeam comTempTeam = ComTempTeam.builder() // 빌드
        .teamCd(section.getSectionId()) // 팀코드
        .teamName(section.getSectionName()) // 이름
        .teamSort(teamSort) // 순서
        .teamInfo(section.getSectionContents()) // 내용
        .orgCd(orgCd) // 부서코드
        .teamUse(checkTeamUse(section)) // 삭제
        .build();
    return comTempTeam;
  }
  
  private boolean checkTeamUse(PotOrgSection section) {
    boolean isUsed = "Y".equals(section.getUseYn());
    boolean isMarked = "Y".equals(section.getMarkYn());
    
    return isUsed && isMarked;
  }

  private ComTempOrg convertToTempOrg(PotOrgDept dept) {
    // 부서코드 가공
    String orgCd = "";
    try {
      orgCd = dept.getDeptId().length() > 7 ? dept.getDeptId().substring(0, 7) : dept.getDeptId();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    // 상위 부서코드 가공
    String orgUprCd = "";
    try {
      orgUprCd = dept.getUpDeptId().length() > 7 ? dept.getUpDeptId().substring(0, 7) : dept.getUpDeptId();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    // 부서 순서
    Integer orgSort = 0;
    try {
      orgSort = Integer.parseInt(dept.getOdr());
    } catch (Exception e) {
      orgSort = 0;
    }
    ComTempOrg comTempOrg = ComTempOrg.builder() // 빌더
        .orgCd(orgCd) // 부서코드
        .orgUprCd(orgUprCd) // 상위 부소커드
        .orgName(dept.getDeptName()) // 이름
        .orgFullname(dept.getFullDeptName()) // 전체 이름
        .orgEnglishName(null) // 영어 이름
        .orgSort(orgSort) // 순서
        .orgUse(checkOrgUse(dept)) // 삭제
        .build();
    return comTempOrg;
  }
  
  private boolean checkOrgUse(PotOrgDept dept) {
    boolean isUsed = "1".equals(dept.getUseYn());
    boolean isMarked = "Y".equals(dept.getMarkYn());
    
    return isUsed && isMarked;
  }

  private ComStaff convertToComStaff(ComTempStaff comTempStaff) {
    ComStaff comStaff = null;
    try {
      comStaff = comStaffRepo.findByStaffLogonId(comTempStaff.getStaffLogonId());
    } catch (Exception e) {
    } finally {
      if (comStaff == null) {
        comStaff = new ComStaff();
        comStaff.setStaffLogonId(comTempStaff.getStaffLogonId());
        comStaff.setStaffSync(Sync.sync);
        comStaff.setStaffVisible(true);
      }
    }
    if (comStaff.getStaffSync() != Sync.sync)
      return comStaff;

    comStaff.setStaffBirth(comTempStaff.getStaffBirth());
    comStaff.setStaffName(comTempStaff.getStaffName());
    comStaff.setStaffTelno(comTempStaff.getStaffTelno());
    comStaff.setStaffPosition(comTempStaff.getStaffPosition());
    comStaff.setStaffStatus(comTempStaff.getStaffStatus());
    if (!comStaff.getStaffStatus().equals(Status.AAA) && !comStaff.getStaffStatus().equals(Status.AAB)) {
      comStaff.setStaffSort(99);
    } else {
      comStaff.setStaffSort(comTempStaff.getStaffSort());
    }
    comStaff.setStaffEmail(comTempStaff.getStaffEmail());
    comStaff.setStaffFaxno(comTempStaff.getStaffFaxno());
    comStaff.setStaffPhone(comTempStaff.getStaffPhone());
    comStaff.setStaffDuty(comTempStaff.getStaffDuty());
    comStaff.setStaffPresent(true);
    comStaff.setStaffGrade(comTempStaff.getStaffGrade());
    comStaff.setStaffAppointDate(comTempStaff.getStaffAppointDate());
    if (comTempStaff.getStaffLogonId() != null) {
//      String upperStaffLogonId = comTempStaff.getStaffLogonId().toUpperCase();
      comStaff.setStaffFilename(null);
      comStaff.setStaffOriginFilename(null);
    }
    try {
      comStaff.setComOrg(comOrgRepo.findOne(comTempStaff.getOrgCd()));
    } catch (Exception e) {
    }

    try {
      comStaff.setComTeam(comTeamRepo.findOne(comTempStaff.getTeamCd()));
    } catch (Exception e) {
    }
    return comStaff;
  }

  private ComTeam convertToComTeam(ComTempTeam comTempTeam) {
    ComTeam comTeam = null;
    comTeam = comTeamRepo.findOne(comTempTeam.getTeamCd());
    if (comTeam == null) {
      comTeam = new ComTeam();
      comTeam.setTeamCd(comTempTeam.getTeamCd());
      comTeam.setTeamName(comTempTeam.getTeamName());
      comTeam.setTeamSort(comTempTeam.getTeamSort());
      comTeam.setTeamVisible(true);
      comTeam.setTeamSync(true);
    }
    comTeam.setTeamInfo(comTempTeam.getTeamInfo());
    try {
      comTeam.setComOrg(comOrgRepo.findOne(comTempTeam.getOrgCd()));
    } catch (Exception e) {
    }
    return comTeam;
  }

  private ComOrg convertToComOrg(ComTempOrg comTempOrg) {
    ComOrg comOrg = null;
    comOrg = comOrgRepo.findOne(comTempOrg.getOrgCd());
    if (comOrg == null) {
      // INSERT
      comOrg = new ComOrg();
      comOrg.setOrgCd(comTempOrg.getOrgCd());

      comOrg.setOrgName(comTempOrg.getOrgName());
    }
    if (comTempOrg.getOrgName().equals("진천군"))
      comOrg.setOrgFullname("진천군");
    // UPDATE
    comOrg.setOrgUprCd(comTempOrg.getOrgUprCd());
    comOrg.setOrgEnglishName(comTempOrg.getOrgEnglishName());
    comOrg.setOrgSort(comTempOrg.getOrgSort());
    comOrg.setOrgDirection(comTempOrg.getOrgDirection());
    return comOrg;
  }

  // ===================================TRANS=====================================
  // 이곳에서 위 CONVERT 및 유틸 메소드등을 호출하여 코드 실행

  public void transToTempStaff(String orgCd) throws Exception {
    syncStaffRepo.deleteAll();
    List<PotOrgUser> users = syncUmsMapper.getSyncStaffs(orgCd);
    syncStaffRepo.save(users);
    comTempStaffRepo.deleteAll();
    for (PotOrgUser user : users) {
      comTempStaffRepo.save(convertToTempStaff(user));
    }
  }

  public void transToTempTeam(String orgCd) throws Exception {
    syncTeamRepo.deleteAll();
    List<PotOrgSection> sections = syncUmsMapper.getSyncTeams(orgCd);
    syncTeamRepo.save(sections);
    comTempTeamRepo.deleteAll();
    for (PotOrgSection section : sections) {
      comTempTeamRepo.save(convertToTempTeam(section));
    }
  }

  public void transToTempOrg(String orgCd) throws Exception {
    syncOrgRepo.deleteAll();
    List<PotOrgDept> depts = syncUmsMapper.getSyncOrgs(orgCd);
    syncOrgRepo.save(depts);
    comTempOrgRepo.deleteAll();
    for (PotOrgDept dept : depts) {
      comTempOrgRepo.save(convertToTempOrg(dept));
    }
  }

  public void transToComStaff() throws Exception {
    List<ComTempStaff> comTempStaffs = comTempStaffRepo.findAll();
    for (ComTempStaff comTempStaff : comTempStaffs) {
      if (comTempStaff.getStaffUse())
        comStaffRepo.save(convertToComStaff(comTempStaff));
      else
        try {
          comStaffRepo.deleteByStaffLogonId(comTempStaff.getStaffLogonId());
        } catch (Exception e) {
        }
    }
//    comStaffRepo.clearInvalidTeamCd();
  }

  public void transToComTeam() throws Exception {
    List<ComTempTeam> comTempTeams = comTempTeamRepo.findAll();
    for (ComTempTeam comTempTeam : comTempTeams) {
      if (comTempTeam.getTeamUse())
        comTeamRepo.save(convertToComTeam(comTempTeam));
      else
        try {
          comTeamRepo.delete(comTempTeam.getTeamCd());
        } catch (Exception e) {
        }
    }
  }

  public void transToComOrg() throws Exception {
    List<ComTempOrg> comTempOrgs = comTempOrgRepo.findAll();
    for (ComTempOrg comTempOrg : comTempOrgs) {
      if (comTempOrg.getOrgUse())
        comOrgRepo.save(convertToComOrg(comTempOrg));
      else
        try {
          comOrgRepo.delete(comTempOrg.getOrgCd());
        } catch (Exception e) {
        }
    }

    for (int i = 0; i < 3; i++) {
      comOrgRepo.makeOrgFullname();
    }

  }

}
