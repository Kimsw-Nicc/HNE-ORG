package egovframework.nicc.hne.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.nicc.hne.com.service.OrgService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/com")
@Log4j2
public class ComController {

  @Autowired
  OrgService orgService;

  @GetMapping("/manage")
  public void goManage() {
    log.info("goManage");
  }
  // 여긴 리스트

  @GetMapping("/org/bureau")
  public void goBureau() {
    log.info("/com/org/bureau");
  }

  @GetMapping("/org/section")
  public void goSection() {
    log.info("/com/org/section");
  }

  @GetMapping("/org/team")
  public void goTeam() {
    log.info("/com/org/team");
  }

  @GetMapping("/staff/list")
  public void goList() {
    log.info("/com/staff/list");
  }

  @GetMapping("/staff/detail")
  public void goDetail() {
    log.info("/com/staff/detail");
  }

  @GetMapping("/staff/insert")
  public void goInsert() {
    log.info("/com/staff/insert");
  }

  @GetMapping("/staff/update")
  public void goModifyPage() {
    log.info("/com/staff/update");
  }

  @GetMapping("/staff/moveStaff")
  public void goMoveStaff() {
    log.info("/com/staff/moveStaff");
  }

  @GetMapping("/staff/sortStaff")
  public void goPriority() {
    log.info("/com/staff/sortStaff");
  }

}
