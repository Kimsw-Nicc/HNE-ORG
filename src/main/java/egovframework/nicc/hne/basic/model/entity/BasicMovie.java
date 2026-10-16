package egovframework.nicc.hne.basic.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

import egovframework.nicc.hne.com.model.entity.ComOrg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "basic_movie")
@EntityListeners(AuditingEntityListener.class)
public class BasicMovie {

  @Id
  @Column(name = "movie_cd")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer movieCd;
  @Column(name = "movie_name")
  private String movieName;
  @Column(name = "movie_sort")
  private Integer movieSort;
  @Column(name = "movie_filename")
  private String movieFilename;
  @Column(name = "movie_origin_filename")
  private String movieOriginFilename;
  @Column(name = "movie_thumbnail")
  private String movieThumbnail;
  @Column(name = "movie_ndt")
  @CreatedDate
  private Date movieNdt;
  @Column(name = "movie_edt")
  @LastModifiedDate
  private Date movieEdt;
  
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "org_cd")
  private ComOrg comOrg;
  
}