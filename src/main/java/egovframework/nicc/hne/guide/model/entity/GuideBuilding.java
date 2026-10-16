package egovframework.nicc.hne.guide.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// import egovframework.nicc.hne.com.model.entity.ComStaff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "guide_building")
public class GuideBuilding {

	@Id
	@Column(name = "building_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer buildingId;
	@Column(name = "building_name")
	private String buildingName;
	@Column(name = "building_sort")
	private Integer buildingSort;
	@Column(name = "building_filename")
  private String buildingFilename;
	@Column(name = "building_origin_filename")
  private String buildingOriginFilename; 

	@OneToMany(mappedBy = "guideBuilding", fetch = FetchType.LAZY)
	@Builder.Default
	private List<GuideFloor> floors = new ArrayList<>();

}
