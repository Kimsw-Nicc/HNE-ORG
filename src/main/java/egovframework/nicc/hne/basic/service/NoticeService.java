package egovframework.nicc.hne.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.basic.model.dto.BasicNoticeDTO;
import egovframework.nicc.hne.basic.model.entity.BasicNotice;
import egovframework.nicc.hne.basic.repo.BasicNoticeRepo;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class NoticeService {

  @Autowired
  BasicNoticeRepo noticeRepo;
  @Autowired
  ComOrgRepo orgRepo;

  @Autowired
  ModelConverter modelConverter;

  // 페이징 전부 가져오기
  public List<BasicNoticeDTO> getNotices(Pageable pageable, String keyword) {
    List<BasicNotice> notices = noticeRepo.findAll(pageable, keyword);
    return modelConverter.mapList(notices, BasicNoticeDTO.class);
  }

  // 페이징 부서코드로 가져오기
  public List<BasicNoticeDTO> getNotices(Pageable pageable, String keyword, String orgCd) {
    List<BasicNotice> notices = noticeRepo.findByOrgCd(pageable, keyword, orgCd);
    return modelConverter.mapList(notices, BasicNoticeDTO.class);
  }

  // 일반 전부 가져오기
  public List<BasicNoticeDTO> getNoticesAll() {
    return modelConverter.mapList(noticeRepo.findAll(), BasicNoticeDTO.class);
  }

  // 일반 부서코드로 가져오기
  public List<BasicNoticeDTO> getNoticesAll(String orgCd) {
    return modelConverter.mapList(noticeRepo.findByOrgCd(orgCd), BasicNoticeDTO.class);
  }
  
  // NULL 가져오기
  public List<BasicNoticeDTO> getNoticesNull() {
    return modelConverter.mapList(noticeRepo.findByOrgCdIsNull(), BasicNoticeDTO.class);
  }

  // 하나 가져오기
  public BasicNoticeDTO getNotice(Integer noticeCd) {
    return modelConverter.map(noticeRepo.findOne(noticeCd), BasicNoticeDTO.class);
  }

  // 개수 가져오기
  public Long getCount(String keyword, String orgCd) {
    if (orgCd.equals("none"))
      return noticeRepo.count(keyword);
    else
      return noticeRepo.count(keyword, orgCd);
  }

  // INSERT
  public void insertNotice(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = modelConverter.map(noticeDTO, BasicNotice.class);
    notice.setComOrg(orgRepo.findOne(noticeDTO.getOrgCd()));
    noticeRepo.save(notice);
  }

  // UPDATE
  public String updateNoticeFilename(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = noticeRepo.findOne(noticeDTO.getNoticeCd());
    String filename = notice.getNoticeFilename();
    notice.setNoticeFilename(noticeDTO.getNoticeFilename());
    noticeRepo.save(notice);
    if (filename.equals(noticeDTO.getNoticeFilename())) {
      return null;
    }
    return filename;
  }

  public void updateNoticeOriginFilename(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = noticeRepo.findOne(noticeDTO.getNoticeCd());
    notice.setNoticeOriginFilename(noticeDTO.getNoticeOriginFilename());
    noticeRepo.save(notice);
  }

  public void updateNoticeName(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = noticeRepo.findOne(noticeDTO.getNoticeCd());
    notice.setNoticeName(noticeDTO.getNoticeName());
    noticeRepo.save(notice);
  }

  public void updateNoticeContent(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = noticeRepo.findOne(noticeDTO.getNoticeCd());
    notice.setNoticeContent(noticeDTO.getNoticeContent());
    noticeRepo.save(notice);
  }

  public void updateNoticeStartDate(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = noticeRepo.findOne(noticeDTO.getNoticeCd());
    notice.setNoticeStartDate(noticeDTO.getNoticeStartDate());
    noticeRepo.save(notice);
  }

  public void updateNoticeEndDate(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = noticeRepo.findOne(noticeDTO.getNoticeCd());
    notice.setNoticeEndDate(noticeDTO.getNoticeEndDate());
    noticeRepo.save(notice);
  }

  public void updateNoticeSort(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = noticeRepo.findOne(noticeDTO.getNoticeCd());
    notice.setNoticeSort(noticeDTO.getNoticeSort());
    noticeRepo.save(notice);
  }

  public void updateNoticeOrg(BasicNoticeDTO noticeDTO) {
    BasicNotice notice = noticeRepo.findOne(noticeDTO.getNoticeCd());
    notice.setComOrg(orgRepo.findOne(noticeDTO.getOrgCd()));
    noticeRepo.save(notice);
  }

  // DELETE
  public String deleteNotice(Integer noticeCd) {
    BasicNotice notice = noticeRepo.findOne(noticeCd);
    noticeRepo.delete(noticeCd);
    return notice.getNoticeFilename();
  }

}
