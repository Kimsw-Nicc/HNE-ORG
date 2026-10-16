package egovframework.nicc.hne.com.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import egovframework.nicc.hne.com.model.enumeration.Status;
import egovframework.nicc.hne.com.model.enumeration.Sync;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComStaffDTO {

  private Integer staffCd;
  private String staffName;
  private String staffPosition;
  private String staffTelno;
  private String staffFaxno;
  private String staffPhone;
  private String staffBirth;
  private String staffFilename;
  private String staffOriginFilename;
  private String staffEmail;
  private String staffLogonId;
  private Integer staffSort;
  private Date staffNdt;
  private Date staffEdt;
  private String staffDuty;
  private Boolean staffVisible;
  private Sync staffSync;
  private Status staffStatus;
  private Boolean staffPresent;
  private Boolean staffDeleted;
  // 
  private String staffGrade;
  private Date staffGradeAppointDate;
  private Date staffAppointDate;
  private Date staffOrgAppointDate;

  private String orgCd;
  private String orgName;
  private String orgFullname;
  private String orgFullcode;
  private String teamCd;
  private String teamName;

  @JsonProperty("staffStatusString")
  public String getStaffStatusMeaning() {
      return staffStatus != null ? staffStatus.getMeaning() : null;
  }
}
