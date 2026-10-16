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
public class ComOrgDTO {
  private String orgCd;
  private String orgUprCd;
  private String orgName;
  private String orgFullname;
  private String orgEnglishName;
  private Integer orgSort;
  private Boolean orgDirection;
  private Date orgNdt;
  private Date orgEdt;
  private String orgLocation;
  
  private String orgUprName;

//  List<ComTeamDTO> teams = new ArrayList<>();
//  List<ComStaffDTO> staffs = new ArrayList<>();

  private Boolean isAuthUser;
  private Integer comStaffCount;

}
