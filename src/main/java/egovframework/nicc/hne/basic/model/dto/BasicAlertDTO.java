package egovframework.nicc.hne.basic.model.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import egovframework.nicc.hne.basic.model.entity.BasicAlert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicAlertDTO {

  private Integer alertCd;
  private String alertContent;
  @Enumerated(EnumType.STRING)
  private BasicAlert.Category alertCategory;
  @Enumerated(EnumType.STRING)
  private BasicAlert.Type alertType;
  private Boolean alertVisible;
  private Date alertNdt;
  private Date alertEdt;
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
  private Date alertStartDate;
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
  private Date alertEndDate;
  @JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Seoul")
  private Timestamp alertStartTime;
  @JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Seoul")
  private Timestamp alertEndTime;

}
