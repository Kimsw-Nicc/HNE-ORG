package egovframework.nicc.hne.client.model.jsonvo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import egovframework.nicc.hne.com.model.dto.ComOrgDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ComOrgVO {
//  private String dep_code;
//  private String upr_dept_code;
//  private String dep_code_nm;
//  private String dep_full_nm;
//  private String dept_seq;
//  private String dept_num;
//  private String dept_rank;
//  private String rep_tel_no;
//  private String rep_fax_no;
//  private String cre_dt;
//  private String mod_dt;
//  private String dep_location;
  private String depCode;
  private String uprDeptCode;
  private String depCodeNm;
  private String depFullNm;
  private String deptSeq;
  private String deptNum;
  private String deptRank;
  private String repTelNo;
  private String repFaxNo;
  private String creDt;
  private String modDt;
  private String depLocation;

  public ComOrgVO(ComOrgDTO orgDTO){
    depCode = orgDTO.getOrgCd();
    uprDeptCode = orgDTO.getOrgUprCd();
    depCodeNm = orgDTO.getOrgName();
    depFullNm = orgDTO.getOrgFullname();
    try {
      deptSeq = orgDTO.getOrgSort().toString();
    }catch(Exception e) {}
    creDt = VOUtil.dateToString(orgDTO.getOrgNdt());
    modDt = VOUtil.dateToString(orgDTO.getOrgEdt());
    depLocation = orgDTO.getOrgLocation();
  }
}