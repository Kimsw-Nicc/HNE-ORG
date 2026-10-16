package egovframework.nicc.hne.layout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.layout.model.dto.LayoutBackgroundDTO;
import egovframework.nicc.hne.layout.service.LayoutBackgroundService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/layout")
@Log4j2
public class LayoutController {

  @Autowired
  LayoutBackgroundService backgroundService;
  @Autowired
  VolService volService;

  @GetMapping("/list")
  public void goList() {
    log.info("/layout/list");
  }

  @GetMapping(value = "/background")
  @ResponseBody
  public LayoutBackgroundDTO getBackgroundByOrgCd(@RequestParam String orgCd) throws Exception {
    log.info("/layout/background?orgCd={}", orgCd);
    return backgroundService.getBackgroundByOrgCd(orgCd);
  }

  @PostMapping(value = "/background/insert")
  @ResponseBody
  public void insertBackground(@RequestBody LayoutBackgroundDTO backgroundDTO) {
    log.info("/layout/background/insert {}", backgroundDTO);
    backgroundService.deleteBackgroundByOrgCd(backgroundDTO.getOrgCd());
    backgroundService.insertBackground(backgroundDTO);
    volService.updateClientVol(VolService.LAYOUT);
  }
}
