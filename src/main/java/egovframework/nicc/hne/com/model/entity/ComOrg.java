package egovframework.nicc.hne.com.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import egovframework.nicc.hne.auth.model.entity.AuthUser;
import egovframework.nicc.hne.basic.model.entity.BasicGallery;
import egovframework.nicc.hne.basic.model.entity.BasicMovie;
import egovframework.nicc.hne.basic.model.entity.BasicNotice;
import egovframework.nicc.hne.guide.model.entity.GuideText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "com_org")
@EntityListeners(AuditingEntityListener.class)
public class ComOrg {
  @Id
  @Column(name="org_cd")
  private String orgCd;
  @Column(name="org_upr_cd")
  private String orgUprCd;
  @Column(name="org_name")
  private String orgName;
  @Column(name="org_fullname")
  private String orgFullname;
  @Column(name="org_english_name")
  private String orgEnglishName;
  @Column(name="org_sort")
  private Integer orgSort;
  @Column(name="org_direction")
  private Boolean orgDirection;
  @Column(name="org_ndt")
  @CreatedDate
  private Date orgNdt;
  @Column(name="org_edt")
  @LastModifiedDate
  private Date orgEdt;
  @Column(name="org_location")
  private String orgLocation;

  @OneToMany(mappedBy = "comOrg", fetch = FetchType.LAZY)
  private List<ComTeam> teams;
  @OneToMany(mappedBy = "comOrg", fetch = FetchType.LAZY)
  private List<ComStaff> staffs;
  @OneToMany(mappedBy = "comOrg", fetch = FetchType.LAZY)
  private List<GuideText> texts;
  @OneToMany(mappedBy = "comOrg", fetch = FetchType.LAZY)
  private List<AuthUser> users;
  @OneToMany(mappedBy = "comOrg", fetch = FetchType.LAZY)
  private List<BasicGallery> galleries;
  @OneToMany(mappedBy = "comOrg", fetch = FetchType.LAZY)
  private List<BasicNotice> notices;
  @OneToMany(mappedBy = "comOrg", fetch = FetchType.LAZY)
  private List<BasicMovie> movies;
}
