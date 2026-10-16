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
@Table(name = "com_temp_team")
@EntityListeners(AuditingEntityListener.class)
public class ComTempTeam {
  
  @Id
  @Column(name="team_cd")
  private String teamCd;
  @Column(name="team_name")
  private String teamName;
  @Column(name="team_sort")
  private Integer teamSort;
  @Column(name="team_visible")
  private Boolean teamVisible;
  @Column(name="team_ndt")
  @CreatedDate
  private Date teamNdt;
  @Column(name="team_edt")
  @LastModifiedDate
  private Date teamEdt;
  @Column(name="team_info")
  private String teamInfo;
  @Column(name="org_cd")
  private String orgCd;

  
  @Column(name = "team_use")
  private Boolean teamUse;
}
