package egovframework.nicc.hne.client.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.nicc.hne.basic.model.dto.BasicAlertDTO;
import egovframework.nicc.hne.basic.model.dto.BasicBackgroundDTO;
import egovframework.nicc.hne.basic.model.dto.BasicGalleryDTO;
import egovframework.nicc.hne.basic.model.dto.BasicMovieDTO;
import egovframework.nicc.hne.basic.model.dto.BasicNoticeDTO;
import egovframework.nicc.hne.basic.model.dto.BasicSettingDTO;
import egovframework.nicc.hne.basic.model.dto.BasicTourDTO;
import egovframework.nicc.hne.basic.service.AlertService;
import egovframework.nicc.hne.basic.service.BackgroundService;
import egovframework.nicc.hne.basic.service.BasicSettingService;
import egovframework.nicc.hne.basic.service.GalleryService;
import egovframework.nicc.hne.basic.service.MovieService;
import egovframework.nicc.hne.basic.service.NoticeService;
import egovframework.nicc.hne.basic.service.TourService;
//import egovframework.nicc.hne.client.model.dto.ClientEquipmentDTO;
import egovframework.nicc.hne.client.model.dto.ClientPatchDTO;
import egovframework.nicc.hne.client.model.jsonvo.BasicAlertVO;
import egovframework.nicc.hne.client.model.jsonvo.BasicBackgroundVO;
import egovframework.nicc.hne.client.model.jsonvo.BasicGalleryVO;
import egovframework.nicc.hne.client.model.jsonvo.BasicMovieVO;
import egovframework.nicc.hne.client.model.jsonvo.BasicNoticeVO;
import egovframework.nicc.hne.client.model.jsonvo.BasicSettingVO;
import egovframework.nicc.hne.client.model.jsonvo.BasicTourVO;
import egovframework.nicc.hne.client.model.jsonvo.ClientPatchVO;
import egovframework.nicc.hne.client.model.jsonvo.ClientPatchsVO;
import egovframework.nicc.hne.client.model.jsonvo.ClientVolVO;
import egovframework.nicc.hne.client.model.jsonvo.ComMateVO;
import egovframework.nicc.hne.client.model.jsonvo.ComOrgVO;
import egovframework.nicc.hne.client.model.jsonvo.ComStaffBoxVO;
import egovframework.nicc.hne.client.model.jsonvo.ComTeamVO;
import egovframework.nicc.hne.client.model.jsonvo.ComTeamsVO;
import egovframework.nicc.hne.client.model.jsonvo.GuideBuildingVO;
import egovframework.nicc.hne.client.model.jsonvo.GuideFloorVO;
import egovframework.nicc.hne.client.model.jsonvo.GuideTextVO;
import egovframework.nicc.hne.client.model.jsonvo.LayoutBackgroundVO;
//import egovframework.nicc.hne.client.service.EquipmentService;
import egovframework.nicc.hne.client.service.PatchService;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.model.dto.ComOrgDTO;
import egovframework.nicc.hne.com.model.dto.ComStaffDTO;
import egovframework.nicc.hne.com.model.dto.ComTeamDTO;
import egovframework.nicc.hne.com.model.enumeration.Status;
import egovframework.nicc.hne.com.service.OrgService;
import egovframework.nicc.hne.com.service.StaffService;
import egovframework.nicc.hne.com.service.TeamService;
import egovframework.nicc.hne.guide.model.dto.GuideBuildingDTO;
import egovframework.nicc.hne.guide.model.dto.GuideFloorDTO;
import egovframework.nicc.hne.guide.model.dto.GuideTextDTO;
import egovframework.nicc.hne.guide.service.BuildingService;
import egovframework.nicc.hne.guide.service.FloorService;
import egovframework.nicc.hne.guide.service.TextService;
import egovframework.nicc.hne.layout.model.dto.LayoutBackgroundDTO;
import egovframework.nicc.hne.layout.service.LayoutBackgroundService;
//import egovframework.nicc.hne.layout.model.dto.LayoutOrgDTO;
//import egovframework.nicc.hne.layout.model.dto.LayoutStaffDTO;
//import egovframework.nicc.hne.layout.service.LayoutService;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequestMapping("/json")
@Log4j2
public class JsonController {

  @Autowired
  StaffService staffService;
  @Autowired
  VolService volService;
  @Autowired
  TeamService teamService;
  @Autowired
  OrgService orgService;
  @Autowired
  GalleryService galleryService;
  @Autowired
  NoticeService noticeService;
  @Autowired
  AlertService alertService;
  @Autowired
  MovieService movieService;
  @Autowired
  BackgroundService backgroundService;
  @Autowired
  BuildingService buildingService;
  @Autowired
  FloorService floorService;
  @Autowired
  TextService textService;
  @Autowired
  PatchService patchService;
  @Autowired
  TourService tourService;
  @Autowired
  BasicSettingService settingService;
  @Autowired
  LayoutBackgroundService layoutBackgroundService;

  @GetMapping("/getVersion")
  public ResponseEntity<ClientVolVO> getVersion() {
    log.debug("/getVersion");
    ResponseEntity<ClientVolVO> entity = null;
    try {
      entity = new ResponseEntity<ClientVolVO>(new ClientVolVO(volService.getClientVol()), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<ClientVolVO>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getTeam/{orgCd}")
  public ResponseEntity<List<ComTeamVO>> getTeam(@PathVariable("orgCd") String orgCd) {
    log.debug("/getTeam/{}", orgCd);
    ResponseEntity<List<ComTeamVO>> entity = null;
    List<ComTeamDTO> comTeamDTOs = teamService.getTeams(orgCd);
    List<ComTeamVO> comTeamVOs = new ArrayList<>();
    for (ComTeamDTO comTeamDTO : comTeamDTOs) {
      comTeamVOs.add(new ComTeamVO(comTeamDTO));
    }
    try {
      entity = new ResponseEntity<List<ComTeamVO>>(comTeamVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<ComTeamVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getStaff/{orgCd}")
  public ResponseEntity<ComStaffBoxVO> getStaffs(@PathVariable("orgCd") String orgCd) {
    log.debug("/getStaff/{}", orgCd);
    ResponseEntity<ComStaffBoxVO> entity = null;
    List<ComTeamDTO> comTeamDTOs = teamService.getTeams(orgCd);
    List<ComTeamsVO> comTeamsVOs = new ArrayList<>();
    for (ComTeamDTO comTeamDTO : comTeamDTOs) {

      if (!comTeamDTO.getTeamVisible()) {
        continue;
      }

      List<ComStaffDTO> comStaffDTOs = staffService.getStaffsByTeamCd(comTeamDTO.getTeamCd());

      List<ComStaffDTO> zeroStaffs = new ArrayList<>();
      List<ComStaffDTO> validStaffs = new ArrayList<>();

      for (ComStaffDTO comStaffDTO : comStaffDTOs) {

        // 공통 제외 조건
        if (!comStaffDTO.getStaffVisible() || !comStaffDTO.getStaffStatus().equals(Status.AAA)) {
          continue;
        }

        // 0번은 무조건 보존
        if (comStaffDTO.getStaffSort() == 0) {
          zeroStaffs.add(comStaffDTO);
          continue;
        }

        // 1~98만 재정렬 대상
        if (comStaffDTO.getStaffSort() >= 1 && comStaffDTO.getStaffSort() <= 98) {
          validStaffs.add(comStaffDTO);
        }
      }

      // 1~98 영역을 기존 staffSort 기준으로 정렬
      Collections.sort(validStaffs, new Comparator<ComStaffDTO>() {
        @Override
        public int compare(ComStaffDTO o1, ComStaffDTO o2) {
          return Integer.compare(o1.getStaffSort(), o2.getStaffSort());
        }
      });

      List<ComMateVO> comMateVOs = new ArrayList<>();

      // 0번은 그대로
      for (ComStaffDTO comStaffDTO : zeroStaffs) {
        comMateVOs.add(new ComMateVO(comStaffDTO));
      }

      // 나머지는 1부터 연속 번호 부여
      int sort = 1;

      for (ComStaffDTO comStaffDTO : validStaffs) {
        comStaffDTO.setStaffSort(sort++);
        comMateVOs.add(new ComMateVO(comStaffDTO));
      }
      comTeamsVOs.add(new ComTeamsVO(comTeamDTO, comMateVOs));
    }
    ComStaffBoxVO comStaffBoxVO = new ComStaffBoxVO(orgCd, comTeamsVOs);

    try {
      entity = new ResponseEntity<ComStaffBoxVO>(comStaffBoxVO, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<ComStaffBoxVO>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getTeams")
  public ResponseEntity<List<ComOrgVO>> getTeams() {
    log.debug("/getTeams");
    ResponseEntity<List<ComOrgVO>> entity = null;
    List<ComOrgDTO> comOrgDTOs = orgService.getOrgs();
    List<ComOrgVO> comOrgVOs = new ArrayList<>();
    for (ComOrgDTO comOrgDTO : comOrgDTOs) {
      comOrgVOs.add(new ComOrgVO(comOrgDTO));
    }
    try {
      entity = new ResponseEntity<List<ComOrgVO>>(comOrgVOs, HttpStatus.OK);
    } catch (Exception e) {
      log.error("/getTeams error : {}", e);
      entity = new ResponseEntity<List<ComOrgVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getGallery/{orgCd}")
  public ResponseEntity<List<BasicGalleryVO>> getGallery(@PathVariable("orgCd") String orgCd) {
    log.debug("/getGallery/{}", orgCd);
    ResponseEntity<List<BasicGalleryVO>> entity = null;
    List<BasicGalleryVO> galleryVOs = new ArrayList<>();
    List<BasicGalleryDTO> galleryDTOs = new ArrayList<BasicGalleryDTO>();
    if (orgCd.equals("none")) {
      galleryDTOs = galleryService.getGalleriesNull();
    } else {
      galleryDTOs = galleryService.getGalleriesAll(orgCd);
    }
    for (BasicGalleryDTO galleryDTO : galleryDTOs) {
      galleryVOs.add(new BasicGalleryVO(galleryDTO));
    }
    try {
      entity = new ResponseEntity<List<BasicGalleryVO>>(galleryVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<BasicGalleryVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getNotice/{orgCd}")
  public ResponseEntity<List<BasicNoticeVO>> getNotice(@PathVariable("orgCd") String orgCd) {
    log.debug("/getNotice/{}", orgCd);
    ResponseEntity<List<BasicNoticeVO>> entity = null;
    List<BasicNoticeVO> noticeVOs = new ArrayList<>();
    List<BasicNoticeDTO> noticeDTOs = new ArrayList<>();
    if (orgCd.equals("none")) {
      noticeDTOs = noticeService.getNoticesAll();
    } else {
      noticeDTOs = noticeService.getNoticesAll(orgCd);
    }
    for (BasicNoticeDTO noticeDTO : noticeDTOs) {
      noticeVOs.add(new BasicNoticeVO(noticeDTO));
    }
    try {
      entity = new ResponseEntity<List<BasicNoticeVO>>(noticeVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<BasicNoticeVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getVideo/{orgCd}")
  public ResponseEntity<List<BasicMovieVO>> getVideo(@PathVariable("orgCd") String orgCd) {
    log.debug("/getVideo/{}", orgCd);
    ResponseEntity<List<BasicMovieVO>> entity = null;
    List<BasicMovieVO> movieVOs = new ArrayList<>();
    List<BasicMovieDTO> movieDTOs = new ArrayList<>();
    if (orgCd.equals("none")) {
      movieDTOs = movieService.getMoviesAll();
    } else {
      movieDTOs = movieService.getMoviesAll(orgCd);
    }
    for (BasicMovieDTO movieDTO : movieDTOs) {
      movieVOs.add(new BasicMovieVO(movieDTO));
    }
    try {
      entity = new ResponseEntity<List<BasicMovieVO>>(movieVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<BasicMovieVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getAlert")
  public ResponseEntity<List<BasicAlertVO>> getAlert() {
    log.debug("/getAlert");
    ResponseEntity<List<BasicAlertVO>> entity = null;
    List<BasicAlertDTO> basicAlertDTOs = alertService.getAlerts();
    List<BasicAlertVO> basicAlertVOs = new ArrayList<>();
    for (BasicAlertDTO basicAlertDTO : basicAlertDTOs) {
      basicAlertVOs.add(new BasicAlertVO(basicAlertDTO));
    }
    try {
      entity = new ResponseEntity<List<BasicAlertVO>>(basicAlertVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<BasicAlertVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getTour")
  public ResponseEntity<List<BasicTourVO>> getTour() {
    log.debug("/getTour");
    ResponseEntity<List<BasicTourVO>> entity = null;
    List<BasicTourDTO> basicTourDTOs = tourService.getTours();
    List<BasicTourVO> basicTourVOs = new ArrayList<>();
    for (BasicTourDTO basicTourDTO : basicTourDTOs) {
      basicTourVOs.add(new BasicTourVO(basicTourDTO));
    }
    try {
      entity = new ResponseEntity<List<BasicTourVO>>(basicTourVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<BasicTourVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getBgImg/{orgCd}")
  public ResponseEntity<List<BasicBackgroundVO>> getBgImg(@PathVariable("orgCd") String orgCd) {
    log.debug("/getBgImg");
    ResponseEntity<List<BasicBackgroundVO>> entity = null;
    List<BasicBackgroundDTO> backgroundDTOs = backgroundService.getBackgrounds();
    List<BasicBackgroundVO> backgroundVOs = new ArrayList<>();
    for (BasicBackgroundDTO backgroundDTO : backgroundDTOs) {
      log.debug(">> {}", backgroundDTO);
      backgroundVOs.add(new BasicBackgroundVO(backgroundDTO));
    }
    try {
      entity = new ResponseEntity<List<BasicBackgroundVO>>(backgroundVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<BasicBackgroundVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getBuilding")
  public ResponseEntity<List<GuideBuildingVO>> getBuildings() {
    log.debug("/getBuilding");
    ResponseEntity<List<GuideBuildingVO>> entity = null;
    List<GuideBuildingDTO> guideBuildingDTOs = buildingService.getBuildings();
    List<GuideBuildingVO> guideBuildingVOs = new ArrayList<>();
    for (GuideBuildingDTO guideBuildingDTO : guideBuildingDTOs) {
      List<GuideFloorDTO> guideFloorDTOs = floorService.getFloors(guideBuildingDTO.getBuildingId());
      List<GuideFloorVO> guideFloorVOs = new ArrayList<>();
      for (GuideFloorDTO guideFloorDTO : guideFloorDTOs) {
        List<GuideTextDTO> guideTextDTOs = textService.getTexts(guideFloorDTO.getFloorId());
        List<GuideTextVO> guideTextVOs = new ArrayList<>();
        for (GuideTextDTO guideTextDTO : guideTextDTOs) {
          guideTextVOs.add(new GuideTextVO(guideTextDTO));
        }
        guideFloorVOs.add(new GuideFloorVO(guideFloorDTO, guideTextVOs));
      }
      guideBuildingVOs.add(new GuideBuildingVO(guideBuildingDTO, guideFloorVOs));
    }
    try {
      entity = new ResponseEntity<List<GuideBuildingVO>>(guideBuildingVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<GuideBuildingVO>>(HttpStatus.BAD_REQUEST);
    }

    return entity;
  }

  @GetMapping("/getBasicSetting")
  public ResponseEntity<List<BasicSettingVO>> getBasicSetting() {
    log.debug("/getBasicSetting");
    ResponseEntity<List<BasicSettingVO>> entity = null;
    List<BasicSettingDTO> basicSettingDTOs = settingService.getSettings();
    List<BasicSettingVO> basicSettingVOs = new ArrayList<>();
    for (BasicSettingDTO basicSettingDTO : basicSettingDTOs) {
      basicSettingVOs.add(new BasicSettingVO(basicSettingDTO));
    }
    try {
      entity = new ResponseEntity<List<BasicSettingVO>>(basicSettingVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<BasicSettingVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getPatchFile")
  public ResponseEntity<List<ClientPatchVO>> getPatchFile() {
    log.debug("/getPatchFile");
    ResponseEntity<List<ClientPatchVO>> entity = null;
    List<ClientPatchDTO> clientPatchDTOs = patchService.getPatchAll();
    List<ClientPatchVO> clientPatchVOs = new ArrayList<>();
    for (ClientPatchDTO clientPatchDTO : clientPatchDTOs) {
      clientPatchVOs.add(new ClientPatchVO(clientPatchDTO));
    }
    try {
      entity = new ResponseEntity<List<ClientPatchVO>>(clientPatchVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<ClientPatchVO>>(HttpStatus.BAD_REQUEST);
    }

    return entity;
  }

  @GetMapping(value = "/getPatchAll")
  public ResponseEntity<List<ClientPatchsVO>> getPatchAll() {
    log.debug("/getPatchAll");
    ResponseEntity<List<ClientPatchsVO>> entity = null;

    List<ClientPatchsVO> clientPatchsVOs = patchService.getPatchsAll();

    try {
      entity = new ResponseEntity<List<ClientPatchsVO>>(clientPatchsVOs, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<ClientPatchsVO>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @GetMapping("/getCalendar")
  public void getCalendar() {
    log.debug("/getCalendar");
  }

  @GetMapping("/getLayoutBgImg/{orgCd}")
  public ResponseEntity<LayoutBackgroundVO> getLayoutBackground(@PathVariable String orgCd) throws Exception {
    log.debug("/getLayoutBgImg/{}", orgCd);
    ResponseEntity<LayoutBackgroundVO> entity = null;

    LayoutBackgroundDTO backgroundDTO = layoutBackgroundService.getBackgroundByOrgCd(orgCd);
    try {
      entity = new ResponseEntity<LayoutBackgroundVO>(new LayoutBackgroundVO(backgroundDTO), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<LayoutBackgroundVO>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

}
