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
@Table(name = "pot_org_dept")
public class PotOrgDept {
	@Id
	@Column(name="dept_id")
	private String deptId;
	@Column(name="dept_name")
	private String deptName;
	@Column(name="fulld_dept_name")
	private String fullDeptName;
	@Column(name="up_dept_id")
	private String upDeptId;
	@Column(name="top_dept_id")
	private String topDeptId;
	@Column(name="dept_odr")
	private String deptOdr;
	@Column(name="dept_rank")
	private String deptRank;
	@Column(name="dept_tel")
	private String deptTel;
	@Column(name="dept_fax")
	private String deptFax;
	@Column(name="use_yn")
	private String useYn;
	@Column(name="mark_yn")
	private String markYn;
	@Column(name="odr")
	private String odr;
	@Column(name="saiall_dept_id")
	private String saiallDeptId;
	@Column(name="change_date")
	private String changeDate;
	@Column(name="regist_id")
	private String registId;
	@Column(name="regist_name")
	private String registName;
	@Column(name="reg_date")
	private String regDate;
	@Column(name="lingage_yn")
	private String linkageYn;
}
