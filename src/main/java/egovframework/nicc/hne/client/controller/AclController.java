package egovframework.nicc.hne.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.client.model.dto.ClientAclDTO;
import egovframework.nicc.hne.client.service.AclService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/acl")
@Log4j2
public class AclController {
  final static int ACL_PAGE_SIZE = 10;

  @Autowired
  AclService aclService;

  @GetMapping("/blocking")
  public void blockedPage() {
  	log.info("/acl/blocking");
  }

  @GetMapping("/list")
  public void getMethodName() {
    log.info("/list");
  }
  
  // REST
  
  @GetMapping("/all")
  @ResponseBody
  public List<ClientAclDTO> getAcls(){
  	log.info("/acl/all");
  	return aclService.getAcls();
  }

  @GetMapping("/{aclId}")
  @ResponseBody
  public ClientAclDTO getAcl(@PathVariable Integer aclId) {
    log.info("/{}", aclId);
    return aclService.getAcl(aclId);
  }

  @PostMapping("/insert")
  @ResponseBody
  public void insertAcl(@RequestBody ClientAclDTO clientAclDTO) throws Exception {
    log.info("/insert {}", clientAclDTO);
    aclService.insertAcl(clientAclDTO);
  }

  @PostMapping("/update")
  @ResponseBody
  public void updateAcl(@RequestBody ClientAclDTO clientAclDTO) throws Exception {
    log.info("/update {}", clientAclDTO);
    aclService.updateAcl(clientAclDTO);
  }

  @DeleteMapping("/delete")
  @ResponseBody
  public void deleteAcl(@RequestParam Integer aclId) throws Exception {
    log.info("/delete{}", aclId);
    aclService.deleteAcl(aclId);
  }

}
