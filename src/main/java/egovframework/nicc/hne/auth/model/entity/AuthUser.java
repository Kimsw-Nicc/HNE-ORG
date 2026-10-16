
package egovframework.nicc.hne.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import egovframework.nicc.hne.auth.model.enumeration.Role;
import egovframework.nicc.hne.com.model.entity.ComOrg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "auth_user")
@EntityListeners(AuditingEntityListener.class)
public class AuthUser {

	@Id
	@Column(name = "user_cd")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userCd;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "user_password")
	private String userPassword;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_role")
	@Enumerated(EnumType.STRING)
	private Role userRole;
	@Column(name = "user_session_key")
	private String userSessionKey;
	@Column(name = "user_session_limit")
	private String userSessionLimit;
	@Column(name = "user_ndt")
	@CreatedDate
	private Date userNdt;
	@Column(name = "user_edt")
	@LastModifiedDate
	private Date userEdt;
	@Column(name = "user_failcount")
	private Integer userFailcount;
	@Column(name = "user_lock")
	private Boolean userLock;
	@Column(name = "user_latest_login")
	private Date userLatestLogin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org_cd")
	private ComOrg comOrg;
	
	
}
