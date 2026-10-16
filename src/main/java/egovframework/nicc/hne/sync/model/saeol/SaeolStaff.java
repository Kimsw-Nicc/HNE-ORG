package egovframework.nicc.hne.sync.model.saeol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "saeol_staff")
public class SaeolStaff {

  @Id
  @Column(name = "staff_cd")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer staffCd;
  @Column(name = "logon_id")
  private String logonId;
  @Column(name = "usr_nm")
  private String usrNm;
  @Column(name = "usr_isd")
  private String usrSid;
  @Column(name = "usr_work_state_code")
  private String usrWorkStateCode;
  @Column(name = "engm_se")
  private String engmSe;
  @Column(name = "real_use_dep_code")
  private String realUseDepCode;
  @Column(name = "real_use_dep_nm")
  private String realUseDepNm;
  @Column(name = "clss_no")
  private String clssNo;
  @Column(name = "clss_nm")
  private String clssNm;
  @Column(name = "posit_code")
  private String positCode;
  @Column(name = "posit_nm")
  private String positNm;
  @Column(name = "grade_no")
  private String gradeNo;
  @Column(name = "grade_nm")
  private String gradeNm;
  @Column(name = "email_addr")
  private String emailAddr;
  @Column(name = "telno")
  private String telno;
  @Column(name = "fax_no")
  private String faxNo;
  @Column(name = "etcom_ymd")
  private String etcomYmd;
  @Column(name = "adi_info7")
  private String adiInfo7;
  @Column(name = "cre_dt")
  private String creDt;
  @Column(name = "mod_dt")
  private String modDt;
  @Column(name = "use_yn")
  private String useYn;
  @Column(name = "rduty_name")
  private String rdutyName;
  @Column(name = "usr_rank")
  private String usrRank;
  @Column(name = "blg_team_nm")
  private String blgTeamNm;
  @Column(name = "birth_ymd")
  private String birthYmd;
}
