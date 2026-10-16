package egovframework.nicc.hne.com.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "com_temp_org")
@EntityListeners(AuditingEntityListener.class)
public class ComTempOrg {
  
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
  
  @Column(name="org_use")
  private Boolean orgUse;
}
