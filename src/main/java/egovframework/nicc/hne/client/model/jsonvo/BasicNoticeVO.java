package egovframework.nicc.hne.client.model.jsonvo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import egovframework.nicc.hne.basic.model.dto.BasicNoticeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasicNoticeVO {
  
  private Integer bbsNo;
  private String brdCd;
  private String sectionCd;
  private String bbsTitle;
  private String bbsContent;
  private String bbsFile;
  private String bbsExpSdt;
  private String bbsExpEdt;
  private String bbsNdt;
  private String bbsEdt;
  private String bbsSort;

  public BasicNoticeVO(BasicNoticeDTO noticeDTO) {
    bbsNo = noticeDTO.getNoticeCd();
    // brdCd = noticeDTO.get
    sectionCd = noticeDTO.getOrgCd();
    bbsTitle = noticeDTO.getNoticeName() == null ? "" : noticeDTO.getNoticeName();
    bbsContent = noticeDTO.getNoticeContent() == null ? "" : noticeDTO.getNoticeContent();
    bbsFile = noticeDTO.getNoticeFilename() == null ? "" : noticeDTO.getNoticeFilename();
    bbsExpSdt = VOUtil.dateToString(noticeDTO.getNoticeStartDate(), true);
    bbsExpEdt = VOUtil.dateToString(noticeDTO.getNoticeEndDate(), false);
    bbsNdt = VOUtil.dateToString(noticeDTO.getNoticeNdt());
    bbsEdt = VOUtil.dateToString(noticeDTO.getNoticeEdt());
    bbsSort = noticeDTO.getNoticeSort() == null ? "0" : noticeDTO.getNoticeSort().toString();
  }
}
