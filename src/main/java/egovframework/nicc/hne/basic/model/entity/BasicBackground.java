package egovframework.nicc.hne.basic.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
@Table(name = "basic_background")
@EntityListeners(AuditingEntityListener.class)
public class BasicBackground {

  @Id
  @Column(name = "background_cd")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer backgroundCd;
  @Column(name = "background_name")
  private String backgroundName;
  @Column(name = "background_sort")
  private Integer backgroundSort;
  @Column(name = "background_ndt")
  @CreatedDate
  private Date backgroundNdt;
  @Column(name = "background_edt")
  @LastModifiedDate
  private Date backgroundEdt;
  @Column(name="background_origin_filename")
  private String backgroundOriginFilename;
  @Column(name="background_filename")
  private String backgroundFilename;
 
}