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
@Table(name = "basic_tour")
@EntityListeners(AuditingEntityListener.class)
public class BasicTour {

  @Id
  @Column(name = "tour_cd")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tourCd;
  @Column(name = "tour_name")
  private String tourName;
  @Column(name = "tour_filename")
  private String tourFilename;
  @Column(name = "tour_origin_filename")
  private String tourOriginFilename;
  @Column(name = "tour_ndt")
  @CreatedDate
  private Date tourNdt;
  @Column(name = "tour_edt")
  @LastModifiedDate
  private Date tourEdt;

}
