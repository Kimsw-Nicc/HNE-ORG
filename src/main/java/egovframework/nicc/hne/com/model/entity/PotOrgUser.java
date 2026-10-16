package egovframework.nicc.hne.com.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pot_org_user")
public class PotOrgUser {

	@Id
	@Column(name="user_id")
	private String userId;
	@Column(name="user_name")
	private String userName;
	@Column(name="section_id")
  private String sectionId;
	@Column(name="section_name")
	private String sectionName;
	@Column(name="dept_id")
	private String deptId;
	@Column(name="dept_name")
	private String deptName;
	@Column(name="rank_id")
	private String rankId;
	@Column(name="rank_name")
	private String rankName;
	@Column(name="regularity")
	private String regularity;
	@Column(name="pos_id")
	private String posId;
	@Column(name="pos_name")
	private String posName;
	@Column(name="duty_id")
	private String dutyId;
	@Column(name="duty_name")
	private String dutyName;
	@Column(name="grade_id")
	private String gradeId;
	@Column(name="grade_name")
	private String gradeName;
	@Column(name="img_url")
	private String imgUrl;
	@Column(name="tel")
	private String tel;
	@Column(name="mobile")
	private String mobile;
	@Column(name="mail")
	private String mail;
	@Column(name="birth_date")
	private String birthDate;
	@Column(name="work_division_contents")
	private String workDivisionContents;
	@Column(name="odr")
	private String odr;
	@Column(name="use_yn")
	private String useYn;
	@Column(name="mark_yn")
	private String markYn;
	@Column(name="first_appoint_date")
	private String firstAppointDate;
	@Column(name="dept_appoint_date")
	private String deptAppointDate;
	@Column(name="user_status")
	private String userStatus;
	@Column(name="user_status_name")
	private String userStatusName;
	@Column(name="saiall_user_id")
	private String saiallUserId;
	@Column(name="change_date")
	private String changeDate;
	@Column(name="regist_id")
	private String registId;
	@Column(name="regist_name")
	private String registName;
	@Column(name="reg_date")
	private String regDate;
	@Column(name="linkage_yn")
	private String linkageYn;
	@Column(name="homepage_yn")
	private String homepageYn;
	@Column(name="attachment_yn")
	private String attachmentYn;
}
