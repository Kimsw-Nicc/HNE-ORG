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
@Table(name = "pot_org_section")
public class PotOrgSection {
	@Id
	@Column(name="section_id")
	private String sectionId;
	@Column(name="dept_id")
	private String deptId;
	@Column(name="section_name")
	private String sectionName;
	@Column(name="section_tel")
	private String sectionTel;
	@Column(name="section_fax")
	private String sectionFax;
	@Column(name="section_contents")
	private String sectionContents;
	@Column(name="section_odr")
	private String sectionOdr;
	@Column(name="use_yn")
	private String useYn;
	@Column(name="mark_yn")
	private String markYn;
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
