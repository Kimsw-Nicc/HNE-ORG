package egovframework.nicc.hne.guide.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuideFloorDTO {

	private Integer floorId;
	private String floorName;
	private Date floorNdt;
	private Date floorEdt;
	private Integer floorSort;
	private String floorFilename;
	private String floorOriginFilename;

	private Integer buildingId;

}
