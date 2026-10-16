package egovframework.nicc.hne.com.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "com_team")
@EntityListeners(AuditingEntityListener.class)
public class ComTeam {
  
  @Id
  @Column(name="team_cd")
  private String teamCd;
  @Column(name="team_name")
  private String teamName;
  @Column(name="team_sort")
  private Integer teamSort;
  @Column(name="team_visible")
  private Boolean teamVisible;
  @Column(name="team_sync")
  private Boolean teamSync;
  @Column(name="team_ndt")
  @CreatedDate
  private Date teamNdt;
  @Column(name="team_edt")
  @LastModifiedDate
  private Date teamEdt;
  @Column(name="team_info")
  private String teamInfo;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "org_cd")
  private ComOrg comOrg;

  @OneToMany(mappedBy = "comTeam", fetch = FetchType.LAZY)
  @Builder.Default
  private List<ComStaff> staffs = new ArrayList<>();
}
