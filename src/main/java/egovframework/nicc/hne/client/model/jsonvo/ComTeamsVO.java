package egovframework.nicc.hne.client.model.jsonvo;

import java.util.List;

import egovframework.nicc.hne.com.model.dto.ComTeamDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComTeamsVO {
  private String team_cd;
	private String dep_code;
	private String team_nm;
	private List<ComMateVO> mate;

  public ComTeamsVO(ComTeamDTO comTeamDTO, List<ComMateVO> comMateVOs){
    team_cd = comTeamDTO.getTeamCd();
    dep_code = comTeamDTO.getOrgCd();
    team_nm = comTeamDTO.getTeamName();
    mate = comMateVOs;
  }
}
