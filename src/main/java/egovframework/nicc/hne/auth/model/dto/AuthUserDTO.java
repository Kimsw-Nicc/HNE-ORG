package egovframework.nicc.hne.auth.model.dto;

import java.util.Date;

import egovframework.nicc.hne.auth.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserDTO {
  private Integer userCd;
	private String userId;
	private String userPassword;
	private String userName;
	private Role userRole;
	private String userSessionKey;
	private String userSessionLimit;
	private Date userNdt;
	private Date userEdt;
	private Integer userFailcount;
	private Boolean userLock;
	private Date userLatestLogin;
	
	private String orgCd;
	private String orgName;
	private String orgFullcode;
	private String orgFullname;
	private String orgUprCd;
}
