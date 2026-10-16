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
@Table(name = "basic_notice")
@EntityListeners(AuditingEntityListener.class)
public class BasicNotice {
  
  @Id
  @Column(name = "notice_cd")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer noticeCd;
  @Column(name = "notice_sort")
  private Integer noticeSort;
  @Column(name = "notice_name")
  private String noticeName;
  @Column(name = "notice_content")
  private String noticeContent;
  @Column(name = "notice_filename")
  private String noticeFilename;
  @Column(name = "notice_origin_filename")
  private String noticeOriginFilename;
  @Column(name = "notice_start_date")
  private Date noticeStartDate;
  @Column(name = "notice_end_date")
  private Date noticeEndDate;
  @Column(name = "notice_ndt")
  @CreatedDate
  private Date noticeNdt;
  @Column(name = "notice_edt")
  @LastModifiedDate
  private Date noticeEdt;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "org_cd")
  private ComOrg comOrg;

}
