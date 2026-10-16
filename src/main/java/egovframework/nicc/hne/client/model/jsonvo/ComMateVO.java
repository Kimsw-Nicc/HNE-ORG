package egovframework.nicc.hne.client.model.jsonvo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import egovframework.nicc.hne.com.model.dto.ComStaffDTO;
import egovframework.nicc.hne.com.model.enumeration.Sync;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ComMateVO {

  private Integer stNo;
  private String logonId;
  private String usrNm;
  private String usrSid;
  private String engmSe;
  private String usrWorkStateCode;
  private String usrWorkStateCodeNm;
  private String realUseDepCode;
  private String realUseDepNm;
  private String realUseDepFullNm;
  private String originBlgDepUseYn;
  private String clssNo;
  private String clssNm;
  private String positCode;
  private String positNm;
  private String gradeNo;
  private String gradeNm;
  private String emailAddr;
  private String telno;
  private String faxNo;
  private String etcomYmd;
  private String adiInfo7;
  private String creDt;
  private String modDt;
  private String reflDt;
  private String useYn;
  private String rdutyName;
  private String usrRank;
  private String blgTeamNm;
  private String birthYmd;
  private String visible;
  private String sync;
  private String teamCd;
  private String imgFilenm;

  public ComMateVO(ComStaffDTO staffDTO) {
    stNo = staffDTO.getStaffCd();
    // logon_id = staffDTO.
    usrNm = staffDTO.getStaffName();
    // usr_sid = staffDTO.
    // engm_se = staffDTO.
    if (staffDTO.getStaffPresent())
      usrWorkStateCode = "재실";
    else
      usrWorkStateCode = "부재";
    usrWorkStateCodeNm = staffDTO.getStaffStatus().getMeaning();
    realUseDepCode = staffDTO.getOrgCd();
    realUseDepNm = staffDTO.getOrgName();
    realUseDepFullNm = staffDTO.getOrgFullcode();
    // origin_blg_dep_use_yn;
    // clss_no;
    // clss_nm;
    // posit_code;
    positNm = staffDTO.getStaffPosition();
    // grade_no;
    // grade_nm;
    emailAddr = staffDTO.getStaffEmail();
    telno = staffDTO.getStaffTelno();
    faxNo = staffDTO.getStaffFaxno();
    // etcom_ymd = staffDTO.
    adiInfo7 = staffDTO.getStaffDuty();
    creDt = VOUtil.dateToString(staffDTO.getStaffNdt());
    modDt = VOUtil.dateToString(staffDTO.getStaffEdt());
    // refl_dt;
    // use_yn;
    // rduty_name;
    usrRank = staffDTO.getStaffSort().toString();
    blgTeamNm = staffDTO.getTeamName();
    teamCd = staffDTO.getTeamCd();
    // birth_ymd;
    visible = staffDTO.getStaffVisible() ? "Y" : "N";
    if (staffDTO.getStaffSync() == Sync.sync)
      sync = "Y";
    else if (staffDTO.getStaffSync() == Sync.async)
      sync = "N";
    else if (staffDTO.getStaffSync() == Sync.manual)
      sync = "수동";
    imgFilenm = staffDTO.getStaffFilename();
  }
}
