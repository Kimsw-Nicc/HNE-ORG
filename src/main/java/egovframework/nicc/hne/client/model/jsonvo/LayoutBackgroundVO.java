package egovframework.nicc.hne.client.model.jsonvo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import egovframework.nicc.hne.layout.model.dto.LayoutBackgroundDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LayoutBackgroundVO {

	private Integer biNo;
	private String biImg;
	private boolean biAble;
	private String biNdt;
	private String biEdt;
	private String sectionCd;
	private String sectionName;
	
	public LayoutBackgroundVO(LayoutBackgroundDTO backgroundDTO) {
	  biNo = backgroundDTO.getBackgroundCd();
	  biImg = backgroundDTO.getBackgroundFilename();
    // biAble = backgroundDTO.getable
	  biNdt = VOUtil.dateToString(backgroundDTO.getBackgroundNdt());
	  biEdt = VOUtil.dateToString(backgroundDTO.getBackgroundEdt());
	  sectionCd = backgroundDTO.getOrgCd();
	  sectionName = backgroundDTO.getOrgName();
	}
}
