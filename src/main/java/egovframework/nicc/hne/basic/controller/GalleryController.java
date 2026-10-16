package egovframework.nicc.hne.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.basic.model.dto.BasicGalleryDTO;
import egovframework.nicc.hne.basic.service.GalleryService;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.service.OrgService;
import egovframework.nicc.hne.file.FileService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/gallery/*")
@Log4j2
public class GalleryController {
  final static int GALLERY_PAGE_SIZE = 6;
  final static String GALLERY_DIR = "gallery";

  @Autowired
  GalleryService galleryService;
  @Autowired
  OrgService orgService;
  @Autowired
  VolService volService;
  @Autowired
  FileService fileService;

  @GetMapping("/list")
  public void goList() {
    log.info("/gallery/list");
  }

  @GetMapping("/detail")
  public void goDetail(@RequestParam Integer galleryCd) {
    log.info("/gallery/detail?galleryCd={}", galleryCd);
  }

  @GetMapping("/insert")
  public void goInsert() {
    log.info("/gallery/insert");
  }

  @GetMapping("/update")
  public void goUpdate() {
    log.info("/gallery/update");
  }

  // REST

  @GetMapping("/all")
  @ResponseBody
  public List<BasicGalleryDTO> getGalleries(HttpServletRequest request,
      @PageableDefault(page = 0, size = GALLERY_PAGE_SIZE) Pageable pageable, //
      @RequestParam(defaultValue = "") String orgCd) {
    log.info("/gallery/all {}", pageable);
    String sessionOrgCd = ((AuthUserDTO) request.getSession().getAttribute("login")).getOrgCd();
    List<BasicGalleryDTO> result = null;
    if (sessionOrgCd != null)
      orgCd = sessionOrgCd;
    if (orgCd.equals("none")) {
      result = galleryService.getGalleries(pageable);
    } else {
      result = galleryService.getGalleries(pageable, orgCd);
    }
    return result;
  }

  @GetMapping("/{galleryCd}")
  @ResponseBody
  public BasicGalleryDTO getGallery(@PathVariable Integer galleryCd) {
    log.info("/gallery/{}", galleryCd);
    return galleryService.getGallery(galleryCd);
  }

  @GetMapping("/count")
  @ResponseBody
  public Long countGallery(HttpServletRequest request, @RequestParam(defaultValue = "") String orgCd) {
    String sessionOrgCd = ((AuthUserDTO) request.getSession().getAttribute("login")).getOrgCd();
    if (sessionOrgCd != null)
      orgCd = sessionOrgCd;
    return galleryService.getCount(orgCd);
  }

  @PostMapping("/insert")
  @ResponseBody
  public void doInsert(@RequestBody BasicGalleryDTO galleryDTO) {
    log.info("/gallery/insert : {}", galleryDTO);
    galleryService.insertGallery(galleryDTO);
    volService.updateClientVol(VolService.IMAGE);
  }

  @PutMapping("/update")
  @ResponseBody
  public void doUpdate(@RequestBody BasicGalleryDTO galleryDTO) throws Exception {
    log.info("/gallery/update : {}", galleryDTO);
    galleryService.updateGalleryName(galleryDTO);
    galleryService.updateGallerySort(galleryDTO);
    galleryService.updateGalleryOrg(galleryDTO);
    galleryService.updateGalleryOriginFilename(galleryDTO);
    String filename = galleryService.updateGalleryFilename(galleryDTO);
    fileService.deleteFile(filename, GALLERY_DIR);
    volService.updateClientVol(VolService.IMAGE);
  }

  @PutMapping("/update/sort")
  @ResponseBody
  public void doUpdaetSort(@RequestBody List<BasicGalleryDTO> galleryDTOs) throws Exception {
    log.info("/gallery/update/sort : {}", galleryDTOs);
    for (BasicGalleryDTO basicGalleryDTO : galleryDTOs) {
      galleryService.updateGallerySort(basicGalleryDTO);
    }
    volService.updateClientVol(GALLERY_DIR);
  }

  @DeleteMapping("/delete")
  @ResponseBody
  public void doDelete(@RequestParam Integer galleryCd) throws Exception {
    log.info("/gallery/delete/{}", galleryCd);
    String filename = galleryService.deleteGallery(galleryCd);
    fileService.deleteFile(filename, GALLERY_DIR);
    volService.updateClientVol(VolService.IMAGE);
  }

}
