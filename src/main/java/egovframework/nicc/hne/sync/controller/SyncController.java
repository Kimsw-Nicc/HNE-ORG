package egovframework.nicc.hne.sync.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.service.OrgService;
import egovframework.nicc.hne.sync.model.dto.SyncHistoryDTO;
import egovframework.nicc.hne.sync.model.dto.SyncScheduleDTO;
import egovframework.nicc.hne.sync.service.HistoryService;
import egovframework.nicc.hne.sync.service.SyncService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/sync")
@Log4j2
public class SyncController {

  @Autowired
  OrgService orgService;
  @Autowired
  VolService volService;

  @Autowired
  HistoryService historyService;
  @Autowired
  SyncService syncService;

  @GetMapping("/scheduler")
  public void goScheduler(HttpServletRequest request, Model model) {
    log.info("/scheduler");
    model.addAttribute("login", (AuthUserDTO) request.getSession().getAttribute("login"));
  }
  
  @GetMapping("/schedule")
  @ResponseBody
  public SyncScheduleDTO getSchedule() {
  	log.info("/schedule");
  	return syncService.getSyncSchedule();
  }

  @PostMapping("/schedule")
  @ResponseBody
  public void updateSchedule(@RequestBody SyncScheduleDTO syncScheduleDTO) throws Exception{
    log.info("/schedule : {}", syncScheduleDTO);

    syncService.updateSyncScheduler(syncScheduleDTO);
    syncService.changeCron();
  }

  @PostMapping("/reset")
  @ResponseBody
  public void resetSchedule(){
    log.info("/sync/reset");

    syncService.resetCron();
  }

  @PostMapping("/org")
  @ResponseBody
  public void doSyncOrg(@RequestParam(defaultValue = "") String orgCd) throws Exception {
    log.info("/sync/org {}", orgCd);
    try {
    	syncService.transToTempOrg(orgCd);
      syncService.transToComOrg();
      historyService.saveHistory(true);
    } catch (Exception e) {
      e.printStackTrace();
      historyService.saveHistory(false);
    }
    volService.updateClientVol(VolService.BUREAU);
  }
  
  @PostMapping("/team")
  @ResponseBody
  public void doSyncTeam(@RequestParam(defaultValue = "") String orgCd) throws Exception {
    log.info("/sync/team {}", orgCd);
    try {
      syncService.transToTempTeam(orgCd);
      syncService.transToComTeam();
      historyService.saveHistory(true);
    } catch (Exception e) {
      e.printStackTrace();
      historyService.saveHistory(false);
    }
    volService.updateClientVol(VolService.BUREAU);
  }

  @PostMapping("/staff")
  @ResponseBody
  public void doSyncStaff(@RequestParam(defaultValue = "") String orgCd) throws Exception {
    log.info("/sync/staff {}", orgCd);
    try {
    	syncService.transToTempStaff(orgCd);
      syncService.transToComStaff();
      historyService.saveHistory(true);
    } catch (Exception e) {
      e.printStackTrace();
      historyService.saveHistory(false);
    }
    volService.updateClientVol(VolService.STAFF);
  }
  
  @GetMapping("/history")
  @ResponseBody
  public SyncHistoryDTO getHistory() {
  	log.info("/sync/history");
  	return historyService.getLastHistory();
  }
}
