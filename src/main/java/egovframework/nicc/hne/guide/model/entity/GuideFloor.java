package egovframework.nicc.hne.guide.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "guide_floor")
@EntityListeners(AuditingEntityListener.class)
public class GuideFloor {
  
  @Id
  @Column(name = "floor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer floorId;
  @Column(name = "floor_name")
  private String floorName;
  @Column(name = "floor_ndt")
  @CreatedDate
  private Date floorNdt;
  @Column(name = "floor_edt")
  @LastModifiedDate
  private Date floorEdt;
  @Column(name = "floor_sort")
  private Integer floorSort;
  @Column(name="floor_filename")
  private String floorFilename;
  @Column(name="floor_origin_filename")
  private String floorOriginFilename;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="building_id")
  private GuideBuilding guideBuilding;
  
  @OneToMany(mappedBy="guideFloor", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
  @Builder.Default
  private List<GuideText> texts = new ArrayList();
}
