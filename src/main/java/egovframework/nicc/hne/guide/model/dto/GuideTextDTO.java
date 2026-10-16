package egovframework.nicc.hne.guide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuideTextDTO {

	private Integer textId;
	private String textContent;
	private Integer textSort;
	private Integer textFontSize;
	private String textFontStyle;
	private String textFontFamily;
	private Double textWidth;
	private Double textHeight;
	private Double textTop;
	private Double textLeft;

  private Double textBrX;
  private Double textBrY;
  private Double textTlX;
  private Double textTlY;

	private String teamCds;
	private String orgCd;

	private Integer floorId;
}
