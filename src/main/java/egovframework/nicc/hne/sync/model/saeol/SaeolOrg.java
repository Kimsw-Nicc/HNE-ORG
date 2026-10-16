package egovframework.nicc.hne.sync.model.saeol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "saeol_org")
public class SaeolOrg {

  @Id
  @Column(name = "dep_code")
  private String depCode;
  @Column(name = "dep_code_nm")
  private String depCodeNm;
  @Column(name = "upr_dept_code")
  private String uprDeptCode;
  @Column(name = "dept_seq")
  private String deptSeq;
  @Column(name = "dept_rank")
  private String deptRank;
  @Column(name = "rep_tel_no")
  private String repTelNo;
  @Column(name = "rep_fax_no")
  private String repFaxNo;
  @Column(name = "cre_dt")
  private String creDt;
  @Column(name = "mod_dt")
  private String modDt;
  
}
