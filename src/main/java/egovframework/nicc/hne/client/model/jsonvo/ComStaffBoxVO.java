package egovframework.nicc.hne.client.model.jsonvo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComStaffBoxVO {
  private String dep_code;
	private String section_pass;
	private List<ComTeamsVO> teams;

  public ComStaffBoxVO(String orgCd, List<ComTeamsVO> comTeamsVOs){
    dep_code = orgCd;
    teams = comTeamsVOs;
  }
}