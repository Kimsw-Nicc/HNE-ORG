package egovframework.nicc.hne.client.model.jsonvo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import egovframework.nicc.hne.com.model.dto.ComTeamDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ComTeamVO {
  private String teamCd;
  private String depCode;
  private String teamNm;
  private Integer teamSort;
  private String teamVisible;
  private String teamNdt;
  private String teamEdt;
  private String teamInfo;

  public ComTeamVO(ComTeamDTO comTeamDTO) {
    teamCd = comTeamDTO.getTeamCd();
    depCode = comTeamDTO.getOrgCd();
    teamNm = comTeamDTO.getTeamName();
    teamSort = comTeamDTO.getTeamSort();
    teamVisible = comTeamDTO.getTeamVisible() ? "Y" : "N";
    teamNdt = VOUtil.dateToString(comTeamDTO.getTeamNdt());
    teamEdt = VOUtil.dateToString(comTeamDTO.getTeamEdt());
    teamInfo = comTeamDTO.getTeamInfo();
  }
}
