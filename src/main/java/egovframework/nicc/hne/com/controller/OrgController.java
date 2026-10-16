package egovframework.nicc.hne.com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.model.dto.ComOrgDTO;
import egovframework.nicc.hne.com.model.dto.ComOrgTreeDTO;
import egovframework.nicc.hne.com.service.OrgService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/org")
@Log4j2
public class OrgController {

  @Autowired
  OrgService orgService;
  @Autowired
  VolService volService;

  @GetMapping("/all")
  public List<ComOrgDTO> getOrgs(HttpSession session) {
    log.info("/org/all : {}");

    return orgService.getOrgs();
  }

  @GetMapping("/{orgCd}")
  public ComOrgDTO getOrg(HttpSession session, @PathVariable String orgCd) {
    log.info("/org/{}", orgCd);

    return orgService.getOrg(orgCd);
  }

  @GetMapping("/count")
  public int countOrg() {
    log.info("/org/count");
    return orgService.countAllOrg();
  }

  @PostMapping("/insert")
  public void insertOrg(@RequestBody ComOrgDTO comOrgDTO) {
    log.info("/org/insert : {}", comOrgDTO);

    orgService.insertOrg(comOrgDTO);
    volService.updateClientVol(VolService.BUREAU);
    volService.updateClientVol(VolService.SECTION);
  }

  @PutMapping("/update")
  public void updateOrg(@RequestBody ComOrgDTO comOrgDTO) {
    log.info("/org/update : {}", comOrgDTO);
    orgService.updateOrgSort(comOrgDTO);
    orgService.updateOrgName(comOrgDTO);
    orgService.updateOrgLocation(comOrgDTO);
    volService.updateClientVol(VolService.BUREAU);
    volService.updateClientVol(VolService.SECTION);
  }

  @PutMapping("/update/all")
  public void updateOrgs(@RequestBody List<ComOrgDTO> comOrgDTOs) {
    log.info("/org/update/all : {}", comOrgDTOs);
    for (ComOrgDTO comOrgDTO : comOrgDTOs) {
      orgService.updateOrgSort(comOrgDTO);
      orgService.updateOrgName(comOrgDTO);
    }
    volService.updateClientVol(VolService.BUREAU);
    volService.updateClientVol(VolService.SECTION);
  }

  @DeleteMapping("/delete/{orgCd}")
  public Boolean deleteOrg(@PathVariable String orgCd) {
    log.info("/org/delete/{}", orgCd);

    return orgService.deleteOrg(orgCd);
  }

  // 트리용
  @GetMapping("/tree")
  public List<ComOrgTreeDTO> getOrgTree(HttpSession session) {
    AuthUserDTO authUserDTO = (AuthUserDTO) session.getAttribute("login");
    log.info("/org/tree : {}", authUserDTO);
    return orgService.getOrgTree(authUserDTO);
  }
  
  @GetMapping(value = "/tree/parliament")
  public List<ComOrgTreeDTO> getOrgTreeParliament() {
    log.info("/org/tree/parliament");
    return orgService.getOrgTreeParliament();
  }
  
}
