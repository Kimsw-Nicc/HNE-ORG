package egovframework.nicc.hne.auth.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.auth.model.entity.AuthUser;
import egovframework.nicc.hne.auth.repo.AuthUserRepo;
import egovframework.nicc.hne.com.repo.ComOrgRepo;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class UserService {

	@Autowired
	private AuthUserRepo authUserRepo;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ComOrgRepo comOrgRepo;

	@Autowired
	ModelConverter modelConverter;

	public AuthUserDTO getUserByUserId(String userId) {
		AuthUser authUser = authUserRepo.findByUserId(userId);
		try {
			return modelConverter.map(authUser, AuthUserDTO.class);
		} catch (Exception e) {
			return null;
		}
	}

	public String checkLogin(AuthUserDTO authUserDTO) {
		AuthUser authUser = authUserRepo.findByUserId(authUserDTO.getUserId());
		// 아이디 없음
		if (authUser == null) {
			return "NONE";
		}
		// 잠김
		if (authUser.getUserLock()) {
			return "LOCK";
		}
		// 패스워드 틀림
		if (!passwordEncoder.matches(authUserDTO.getUserPassword(), authUser.getUserPassword())) {
			authUser.setUserFailcount(authUser.getUserFailcount() + 1);
			if (authUser.getUserFailcount() >= 5) {
				authUser.setUserLock(true);
			}
			authUserRepo.save(authUser);
			return "FAIL";
		}
		authUser.setUserFailcount(0);
		authUserRepo.save(authUser);
		return "SUCCESS";
	}

	public Boolean checkPassword(AuthUserDTO authUserDTO) {
		AuthUser authUser = authUserRepo.findByUserId(authUserDTO.getUserId());
		if (passwordEncoder.matches(authUserDTO.getUserPassword(), authUser.getUserPassword())) {
			return true;
		}
		return false;
	}

	@Transactional
	public List<AuthUserDTO> getUsers(String keyword, Pageable pageable) {
		Page<AuthUser> authUserPages = authUserRepo.findByUserNameContaining(keyword, pageable);
		return modelConverter.mapList(authUserPages.getContent(), AuthUserDTO.class);
	}

	public List<AuthUserDTO> getUsersByOrgCd(String orgCd) {
		List<AuthUser> authUsers = authUserRepo.findByComOrg(comOrgRepo.findOne(orgCd));
		return modelConverter.mapList(authUsers, AuthUserDTO.class);
	}

	public void deleteUser(String id) {
		authUserRepo.deleteByUserId(id);
	}

	public void resetUser(String id) {
		AuthUser authUser = authUserRepo.findByUserId(id);
		authUser.setUserPassword(passwordEncoder.encode("qwe123!@#"));
		authUserRepo.save(authUser);
	}

	public void resetAll(AuthUserDTO authUserDTO) {
		List<AuthUser> authUsers = authUserRepo.findAll();
		String encPassword = passwordEncoder.encode(authUserDTO.getUserPassword());
		for (AuthUser authUser : authUsers) {
			authUser.setUserPassword(encPassword);
		}
		authUserRepo.save(authUsers);
	}

	public Boolean duplCheck(String id) {
		return authUserRepo.findByUserId(id) == null;
	}

	public void insertUser(AuthUserDTO authUserDTO) {
		AuthUser authUser = modelConverter.map(authUserDTO, AuthUser.class);
		authUser.setComOrg(comOrgRepo.findOne(authUserDTO.getOrgCd()));
		authUser.setUserPassword(passwordEncoder.encode(authUser.getUserPassword()));
		authUser.setUserFailcount(0);
		authUser.setUserLock(false);
		authUserRepo.save(authUser);
	}

	public void updatePassword(AuthUserDTO authUserDTO) {
		AuthUser authUser = authUserRepo.findByUserId(authUserDTO.getUserId());
		authUser.setUserPassword(passwordEncoder.encode(authUserDTO.getUserPassword()));
		authUserRepo.save(authUser);
	}

	public void updateName(AuthUserDTO authUserDTO) {
		AuthUser authUser = authUserRepo.findByUserId(authUserDTO.getUserId());
		authUser.setUserName(authUserDTO.getUserName());
		authUserRepo.save(authUser);
	}

	public void updateRole(AuthUserDTO authUserDTO) {
		AuthUser authUser = authUserRepo.findByUserId(authUserDTO.getUserId());
		authUser.setUserRole(authUserDTO.getUserRole());
		authUserRepo.save(authUser);
	}

	public void updateFailCount(AuthUserDTO authUserDTO) {
		AuthUser authUser = authUserRepo.findByUserId(authUserDTO.getUserId());
		authUser.setUserFailcount(authUserDTO.getUserFailcount());
		authUserRepo.save(authUser);
	}

	public void updateLock(AuthUserDTO authUserDTO) {
		AuthUser authUser = authUserRepo.findByUserId(authUserDTO.getUserId());
		authUser.setUserLock(authUserDTO.getUserLock());
		authUserRepo.save(authUser);
	}

	public void updateOrg(AuthUserDTO authUserDTO) {
		AuthUser authUser = authUserRepo.findByUserId(authUserDTO.getUserId());
		if (!authUserDTO.getOrgCd().equals("")) {
			authUser.setComOrg(comOrgRepo.findOne(authUserDTO.getOrgCd()));
		} else {
			authUser.setComOrg(null);
		}
		authUserRepo.save(authUser);
	}

	public int countUser(String keyword) {
		return authUserRepo.countAll(keyword);
	}
}
