package egovframework.nicc.hne.com.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Entity
@Table(name = "com_staff")
@EntityListeners(AuditingEntityListener.class)
public class ComStaff {
  @Id
  @Column(name = "staff_cd")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer staffCd;
  @Column(name = "staff_name")
  private String staffName;
  @Column(name = "staff_position")
  private String staffPosition;
  @Column(name = "staff_telno")
  private String staffTelno;
  @Column(name = "staff_faxno")
  private String staffFaxno;
  @Column(name = "staff_phone")
  private String staffPhone;
  @Column(name = "staff_birth")
  private String staffBirth;
  @Column(name = "staff_filename")
  private String staffFilename;
  @Column(name = "staff_origin_filename")
  private String staffOriginFilename;
  @Column(name = "staff_email")
  private String staffEmail;
  @Column(name = "staff_logon_id")
  private String staffLogonId;
  @Column(name = "staff_sort")
  private Integer staffSort;
  @Column(name = "staff_ndt")
  @CreatedDate
  private Date staffNdt;
  @Column(name = "staff_edt")
  @LastModifiedDate
  private Date staffEdt;
  @Column(name = "staff_duty")
  private String staffDuty;
  @Column(name = "staff_visible")
  private Boolean staffVisible;
  @Column(name = "staff_sync")
  @Enumerated(EnumType.STRING)
  private Sync staffSync;
  @Column(name = "staff_status")
  @Enumerated(EnumType.STRING)
  private Status staffStatus;
  @Column(name = "staff_present")
  private Boolean staffPresent;
  //
  @Column(name = "staff_grade")
  private String staffGrade;
  @Column(name = "staff_grade_appoint_date")
  private Date staffGradeAppointDate;
  @Column(name = "staff_appoint_date")
  private Date staffAppointDate;
  @Column(name = "staff_org_appoint_date")
  private Date staffOrgAppointDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "org_cd")
  private ComOrg comOrg;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "team_cd")
  private ComTeam comTeam;

}
