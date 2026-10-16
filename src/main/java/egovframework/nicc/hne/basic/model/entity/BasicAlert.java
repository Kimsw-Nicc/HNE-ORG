package egovframework.nicc.hne.basic.model.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "basic_alert")
@EntityListeners(AuditingEntityListener.class)
public class BasicAlert {

  @Id
  @Column(name = "alert_cd")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer alertCd;
  @Column(name="alert_content")
  private String alertContent;
  @Column(name = "alert_category")
  @Enumerated(EnumType.STRING)
  private Category alertCategory;
  @Column(name = "alert_type")
  @Enumerated(EnumType.STRING)
  private Type alertType;
  @Column(name = "alert_visible")
  private Boolean alertVisible;
  @Column(name = "alert_ndt")
  @CreatedDate
  private Date alertNdt;
  @Column(name = "alert_edt")
  @LastModifiedDate
  private Date alertEdt;
  @Column(name = "alert_start_date")
  private Date alertStartDate;
  @Column(name = "alert_end_date")
  private Date alertEndDate;
  @Column(name = "alert_start_time")
  private Timestamp alertStartTime;
  @Column(name = "alert_end_time")
  private Timestamp alertEndTime;

  public enum Category {
    alert, lunch, message
  }

  public enum Type {
    info, warning, critical
  }
}
