package egovframework.nicc.hne.client.model.jsonvo;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ibm.icu.text.SimpleDateFormat;

import egovframework.nicc.hne.guide.model.dto.GuideFloorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GuideFloorVO {
  private Integer buNo;
	private String buName;
	private String buImg;
	private String buNdt;
	private String buEdt;
	private Integer buType;
	private Integer buSort;
  private List<GuideTextVO> textBox;

  public GuideFloorVO(GuideFloorDTO guideFloorDTO, List<GuideTextVO> guideTextVOs) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
    buNo = guideFloorDTO.getFloorId();
    buName = guideFloorDTO.getFloorName();
    buImg = guideFloorDTO.getFloorFilename();
    buNdt = format.format(guideFloorDTO.getFloorNdt());
    buEdt = format.format(guideFloorDTO.getFloorEdt());
    buType = guideFloorDTO.getBuildingId();
    buSort = guideFloorDTO.getFloorSort();
    textBox = guideTextVOs;
  }
}
