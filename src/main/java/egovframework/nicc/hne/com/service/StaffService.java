package egovframework.nicc.hne.com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.nicc.hne.com.model.dto.ComStaffDTO;
import egovframework.nicc.hne.com.model.entity.ComStaff;
import egovframework.nicc.hne.com.model.entity.ComTeam;
import egovframework.nicc.hne.com.model.enumeration.Sync;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.com.repo.ComStaffRepo;
import egovframework.nicc.hne.com.repo.ComTeamRepo;
import egovframework.nicc.hne.com.repo.impl.ComStaffRepoImpl;
import egovframework.nicc.hne.com.service.StaffService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class StaffService {
  @Autowired
  ComOrgRepo orgRepo;
  @Autowired
  ComTeamRepo teamRepo;
  @Autowired
  ComStaffRepo staffRepo;
  @Autowired
  ComStaffRepoImpl staffRepoImpl;

  @Autowired
  ModelConverter modelConverter;

  public List<ComStaffDTO> getStaffs(Pageable pageable, String orgCd, String teamCd, String keyword, String keywordType,
      String status) {
    if (orgCd.equals("")) { // 널이면 전체 부서 검색
      return modelConverter.mapList(staffRepoImpl.findStaffs(pageable, keyword, keywordType, status),
          ComStaffDTO.class);
    }
    if (teamCd.equals("")) {
      return modelConverter.mapList(staffRepoImpl.findStaffsByOrgCd(pageable, orgCd, keyword, keywordType, status),
          ComStaffDTO.class);
    }
    return modelConverter.mapList(
        staffRepoImpl.findStaffsByOrgCdAndTeamCd(pageable, orgCd, teamCd, keyword, keywordType, status),
        ComStaffDTO.class);
  }

  public int countStaff(String orgCd, String teamCd, String keyword, String keywordType, String status) {
    if (orgCd.equals("")) {
      return staffRepoImpl.countStaffs(keyword, keywordType, status).intValue();
    }
    if (teamCd.equals("")) {
      return staffRepoImpl.countStaffsByOrgCd(orgCd, keyword, keywordType, status).intValue();
    }
    return staffRepoImpl.countStaffsByOrgCdAndTeamCd(orgCd, teamCd, keyword, keywordType, status).intValue();
  }

  public List<ComStaffDTO> getStaffsByOrgCd(String orgCd) {
    return modelConverter.mapList(staffRepo.findByOrgCd(orgCd), ComStaffDTO.class);
  }

  public List<ComStaffDTO> getStaffsByTeamCd(String teamCd) {
    return modelConverter.mapList(staffRepo.findByTeamCd(teamCd), ComStaffDTO.class);
  }

  public List<ComStaffDTO> getStaffsByStaffName(String staffName) {
    return modelConverter.mapList(staffRepo.findByStaffName(staffName), ComStaffDTO.class);
  }

  public ComStaffDTO getStaff(Integer staffCd) {
    return modelConverter.map(staffRepo.findOne(staffCd), ComStaffDTO.class);
  }

  @Transactional
  public List<ComStaffDTO> getEditedStaffs() {
    List<ComStaffDTO> staffDTOs = modelConverter.mapList(staffRepo.findTop8ByOrderByStaffEdtDesc(), ComStaffDTO.class);
    return staffDTOs;
  }

  public void insertStaff(ComStaffDTO staffDTO) {
    ComStaff staff = modelConverter.map(staffDTO, ComStaff.class);
    if (staffDTO.getTeamCd() != null) {
      staff.setComTeam(teamRepo.findOne(staffDTO.getTeamCd()));
    } else {
      staff.setComTeam(null);
    }
    staff.setStaffVisible(true);
    staff.setStaffSync(Sync.manual);
    staff.setStaffOriginFilename(staffDTO.getStaffOriginFilename());
    staff.setStaffFilename(staffDTO.getStaffFilename());
    staff.setStaffPresent(true);
    staffRepo.save(staff);
  }

  public void updateStaffOrg(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    if (staffDTO.getOrgCd() == null)
      return;
    staff.setComOrg(orgRepo.findOne(staffDTO.getOrgCd()));
    staffRepo.save(staff);
  }

  public void updateStaffTeam(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    if (staffDTO.getTeamCd() != null) {
      staff.setComTeam(teamRepo.findOne(staffDTO.getTeamCd()));
    } else {
      staff.setComTeam(null);
    }
    staffRepo.save(staff);
  }

  public String updateStaffFilename(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    String filename = staff.getStaffFilename();
    staff.setStaffFilename(staffDTO.getStaffFilename());
    staffRepo.save(staff);
    if (filename == null || filename.equals(staffDTO.getStaffFilename())) {
      return null;
    }
    return filename;
  }

  public void updateStaffOriginFilename(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffOriginFilename(staffDTO.getStaffOriginFilename());
    staffRepo.save(staff);
  }

  public void updateStaffName(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffName(staffDTO.getStaffName());
    staffRepo.save(staff);
  }

  public void updateStaffPosition(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffPosition(staffDTO.getStaffPosition());
    staffRepo.save(staff);
  }

  public void updateStaffTelno(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffTelno(staffDTO.getStaffTelno());
    staffRepo.save(staff);
  }

  public void updateStaffFaxno(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffFaxno(staffDTO.getStaffFaxno());
    staffRepo.save(staff);
  }

  public void updateStaffEmail(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffEmail(staffDTO.getStaffEmail());
    staffRepo.save(staff);
  }

  public void updateStaffSort(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffSort(staffDTO.getStaffSort());
    staffRepo.save(staff);
  }

  public void updateStaffDuty(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffDuty(staffDTO.getStaffDuty());
    staffRepo.save(staff);
  }

  public void updateStaffStatus(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffStatus(staffDTO.getStaffStatus());
    staffRepo.save(staff);
  }

  public void updateStaffVisible(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffVisible(staffDTO.getStaffVisible());
    staffRepo.save(staff);
  }

  public void updateStaffSync(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffSync(staffDTO.getStaffSync());
    staffRepo.save(staff);
  }

  public void updateStaffPresent(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffPresent(staffDTO.getStaffPresent());
    staffRepo.save(staff);
  }

  public void updateStaffEdt(ComStaffDTO staffDTO) {
    ComStaff staff = staffRepo.findOne(staffDTO.getStaffCd());
    staff.setStaffEdt(new Date());
    staffRepo.save(staff);
  }

  public void deleteStaff(Integer staffCd) {
    staffRepo.delete(staffCd);
  }

  public void sortStaff(String orgCd) {
    List<ComTeam> comTeams;
    if (!orgCd.equals("")) {
      comTeams = teamRepo.findAll(orgCd);
    } else {
      comTeams = teamRepo.findAll();
    }
    for (ComTeam comTeam : comTeams) {
      List<ComStaff> staffs = staffRepo.findByTeamCd(comTeam.getTeamCd());
      List<ComStaff> staffs2 = new ArrayList<ComStaff>();
      int sort = 0;
      for (ComStaff staff : staffs) {
        if (staff.getStaffSort() == 99) {
          continue;
        }
        staff.setStaffSort(sort++);
        staffs2.add(staff);
      }
      staffRepo.save(staffs2);
    }
  }

  public List<ComStaffDTO> findZeroSort() {
    return modelConverter.mapList(staffRepo.findZeroSort(), ComStaffDTO.class);
  }
  
  public void testInvalid() {
    staffRepo.clearInvalidTeamCd();
  }

}
