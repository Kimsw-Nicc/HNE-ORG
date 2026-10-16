package egovframework.nicc.hne.guide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuideBuildingDTO {

	private Integer buildingId;
	private String buildingName;
	private Integer buildingSort;
	private String buildingFilename;
  private String buildingOriginFilename;

}
