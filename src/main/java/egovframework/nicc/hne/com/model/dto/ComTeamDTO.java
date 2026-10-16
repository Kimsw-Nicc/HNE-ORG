package egovframework.nicc.hne.com.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComTeamDTO {
	private String teamCd;
	private String teamName;
	private Integer teamSort;
	private Boolean teamVisible;
  private Boolean teamSync;
	private Date teamNdt;
	private Date teamEdt;
	private String teamInfo;

	private String orgCd;
  private String orgName;

  private Integer comStaffCount;
}
