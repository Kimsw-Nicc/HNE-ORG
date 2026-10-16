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
import egovframework.nicc.hne.basic.model.dto.BasicNoticeDTO;
import egovframework.nicc.hne.basic.service.NoticeService;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.com.service.OrgService;
import egovframework.nicc.hne.file.FileService;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/notice/*")
@Log4j2
public class NoticeController {
  final static int NOTICE_PAGE_SIZE = 10;
  final static String NOTICE_DIR = "notice";

  @Autowired
  NoticeService noticeService;
  @Autowired
  OrgService orgService;
  @Autowired
  VolService volService;
  @Autowired
  FileService fileService;

  @GetMapping("/list")
  public void goList() {
    log.info("/notice/list");
  }

  @GetMapping("/detail")
  public void goReadPage(@RequestParam Integer noticeCd) {
    log.info("/notice/detail?noticeCd={}", noticeCd);
  }

  @GetMapping("/insert")
  public void goInsert() {
    log.info("/notice/insert");
  }

  @GetMapping("/update")
  public void goModifyPage(@RequestParam Integer noticeCd) {
    log.info("/notice/update?noticeCd={}", noticeCd);
  }

  // REST

  @GetMapping("/all")
  @ResponseBody
  public List<BasicNoticeDTO> getNotices(HttpServletRequest request,
      @PageableDefault(page = 0, size = NOTICE_PAGE_SIZE) Pageable pageable,
      @RequestParam(defaultValue = "") String keyword, //
      @RequestParam(defaultValue = "") String orgCd) {
    log.info("/notice/all {}", pageable);
    String sessionOrgCd = ((AuthUserDTO) request.getSession().getAttribute("login")).getOrgCd();
    if (sessionOrgCd != null)
      orgCd = sessionOrgCd;
    List<BasicNoticeDTO> result = null;
    if (orgCd.equals("none")) {
      result = noticeService.getNotices(pageable, keyword);
    } else {
      result = noticeService.getNotices(pageable, keyword, orgCd);
    }
    return result;
  }

  @GetMapping("/{noticeCd}")
  @ResponseBody
  public BasicNoticeDTO getNotice(@PathVariable Integer noticeCd) {
    log.info("/{}", noticeCd);
    return noticeService.getNotice(noticeCd);
  }

  @GetMapping("/count")
  @ResponseBody
  public Long countNotice(HttpServletRequest request, @RequestParam(defaultValue = "") String keyword,
      @RequestParam(defaultValue = "") String orgCd) {
    String sessionOrgCd = ((AuthUserDTO) request.getSession().getAttribute("login")).getOrgCd();
    if (sessionOrgCd != null)
      orgCd = sessionOrgCd;
    return noticeService.getCount(keyword, orgCd);
  }

  @PostMapping("/insert")
  @ResponseBody
  public void doInsert(@RequestBody BasicNoticeDTO noticeDTO) {
    log.info("/notice/insert : {}", noticeDTO);
    noticeService.insertNotice(noticeDTO);
    volService.updateClientVol(VolService.BOARD);
  }

  @PutMapping("/update")
  @ResponseBody
  public void doUpdate(@RequestBody BasicNoticeDTO noticeDTO) throws Exception {
    log.info("/notice/update : {}", noticeDTO);
    noticeService.updateNoticeName(noticeDTO);
    noticeService.updateNoticeContent(noticeDTO);
    noticeService.updateNoticeStartDate(noticeDTO);
    noticeService.updateNoticeEndDate(noticeDTO);
    noticeService.updateNoticeSort(noticeDTO);
    noticeService.updateNoticeOrg(noticeDTO);
    String filename = noticeService.updateNoticeFilename(noticeDTO);
    fileService.deleteFile(filename, NOTICE_DIR);
    noticeService.updateNoticeOriginFilename(noticeDTO);
    volService.updateClientVol(VolService.BOARD);
  }

  @DeleteMapping("/delete")
  @ResponseBody
  public void deleteNotice(@RequestParam Integer noticeCd) throws Exception {
    log.info("/notice/delete/{}", noticeCd);
    String filename = noticeService.deleteNotice(noticeCd);
    fileService.deleteFile(filename, NOTICE_DIR);
    volService.updateClientVol(VolService.BOARD);
  }
}
