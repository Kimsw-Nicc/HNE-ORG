package egovframework.nicc.hne.client.model.jsonvo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import egovframework.nicc.hne.guide.model.dto.GuideTextDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GuideTextVO {

  private String text;
  private String teamCd;
  private String sectionCd;
  private String buNo;
  private String buType;
  private String textType;
  private Integer textSort;
  private Double brX;
  private Double brY;
  private Double tlX;
  private Double tlY;
  @JsonProperty("fontSize")
  private Integer fontSize;
  @JsonProperty("fontStyle")
  private String fontStyle;
  @JsonProperty("fontFamily")
  private String fontFamily;

  private Double width;
  private Double height;
  private Double top;
  private Double bLeft;

  public GuideTextVO(GuideTextDTO guideTextDTO) {
    text = guideTextDTO.getTextContent();
    try {
      teamCd = guideTextDTO.getTeamCds().toString();
    } catch (Exception e) {
      teamCd = "";
    }
    if (guideTextDTO.getOrgCd() == null) {
      sectionCd = "";
    } else {
      sectionCd = guideTextDTO.getOrgCd();
    }

    buNo = guideTextDTO.getFloorId().toString();
    buType = "";
    // textType =
    textSort = guideTextDTO.getTextSort();
    brX = guideTextDTO.getTextBrX();
    brY = guideTextDTO.getTextBrY();
    tlX = guideTextDTO.getTextTlX();
    tlY = guideTextDTO.getTextTlY();
    fontSize = guideTextDTO.getTextFontSize();
    fontStyle = guideTextDTO.getTextFontStyle();
    fontFamily = guideTextDTO.getTextFontFamily();
    width = guideTextDTO.getTextWidth();
    height = guideTextDTO.getTextHeight();
    top = guideTextDTO.getTextTop();
    bLeft = guideTextDTO.getTextLeft();
  }
}
