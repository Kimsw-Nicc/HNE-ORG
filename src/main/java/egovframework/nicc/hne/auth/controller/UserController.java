package egovframework.nicc.hne.auth.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.auth.service.UserService;
import egovframework.nicc.hne.com.service.OrgService;
import lombok.extern.log4j.Log4j2;

@RequestMapping(value = "/user")
@Controller
@Log4j2
public class UserController {
	final static int USER_PAGE_SIZE = 10;

	@Autowired
	UserService userService;
	@Autowired
	OrgService orgService;
	
	@GetMapping("/hidden")
  public void goHidden() {
    log.info("/user/hidden");
  }

	@GetMapping("/list")
	public void goList() {
		log.info("/user/list");
	}

	@GetMapping("/insert")
	public void goInsert() {
		log.info("/user/insert");
	}

	@GetMapping("/update")
	public void goUpdate(String userId) {
		log.info("/user/update?userId={}", userId);
	}

	@GetMapping("/updatePassword")
	public void goUpdatePassword() {
		log.info("/user/updatePassword");
	}

	@GetMapping("/updateOrgPassword")
	public void goUpdateOrgPassword() {
		log.info("/user/updateOrgPassword");
	}
	
	// REST

	@GetMapping("/all")
	@ResponseBody
	public List<AuthUserDTO> getUsers(@PageableDefault(page = 0, size = USER_PAGE_SIZE) Pageable pageable,
			@RequestParam(defaultValue = "") String keyword) {
		log.info("/user/all {}, {}", pageable, keyword);
		return userService.getUsers(keyword, pageable);
	}

  @GetMapping(value="/all", params="orgCd")
  @ResponseBody
	public List<AuthUserDTO> getUsersByOrgCd(@RequestParam String orgCd) throws Exception{
		log.info("/all?orgCd={}",orgCd);
		return userService.getUsersByOrgCd(orgCd);
	}
	
	@GetMapping("/{userId}")
	@ResponseBody
	public AuthUserDTO getUser(@PathVariable String userId) {
		log.info("/user/{}", userId);
		return userService.getUserByUserId(userId);
	}

	@GetMapping("/check")
	@ResponseBody
	public Boolean cehckUser(String userId, String userPassword) {
		log.info("/user/check?userId={}",userId);
		AuthUserDTO authUserDTO = AuthUserDTO.builder()
				.userId(userId)
				.userPassword(userPassword)
				.build();
		return userService.checkPassword(authUserDTO);
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer getMethodName(@RequestParam(defaultValue = "") String keyword) {
		log.info("/user/count?keyword={}", keyword);
		return userService.countUser(keyword);
	}

	@DeleteMapping("/delete/{userId}")
	@ResponseBody
	public void deleteUser(@PathVariable String userId) {
		log.info("/user/delete?userId={}", userId);
		userService.deleteUser(userId);
	}

	@PutMapping("/reset")
	@ResponseBody
	public void resetUser(String userId) {
		log.info("/user/reset?userId={}", userId);
		userService.resetUser(userId);
	}

	@PutMapping("/resetAll")
	@ResponseBody
	public void resetAll(@RequestBody AuthUserDTO authUserDTO) {
		log.info("/user/resetAll : {}", authUserDTO);
		userService.resetAll(authUserDTO);
	}

	@GetMapping("/duplCheck")
	@ResponseBody
	public Boolean duplCheck(String userId) {
		log.info("/user/duplCheck?userId={}", userId);
		return userService.duplCheck(userId);
	}

	@PostMapping("/insert")
	@ResponseBody
	public void insertUser(@RequestBody AuthUserDTO authUserDTO) {
		log.info("/user/insert : {}", authUserDTO);
		userService.insertUser(authUserDTO);
	}

	@PutMapping("/update")
	@ResponseBody
	@Transactional
	public void updateUser(@RequestBody AuthUserDTO authUserDTO) {
		log.info("/user/update : {}", authUserDTO);
		userService.updateName(authUserDTO);
		userService.updateOrg(authUserDTO);
		userService.updateRole(authUserDTO);
	}

	@PutMapping("/updatePassword")
	@ResponseBody
	@Transactional
	public void updatePassword(@RequestBody AuthUserDTO authUserDTO) {
		log.info("/user/updatePassword : {}", authUserDTO);
		userService.updatePassword(authUserDTO);
	}

	@PutMapping("/updateOrgPassword")
	@ResponseBody
	public void updateOrgPassword(@RequestBody AuthUserDTO authUserDTO) {
		log.info("/user/updateOrgPassword : {}", authUserDTO);
		userService.updatePassword(authUserDTO);
	}
	
	@PutMapping("/updateLock")
	@ResponseBody
	public void updateLock(@RequestBody AuthUserDTO authUserDTO) {
		log.info("/user/updateLock {}", authUserDTO);
		userService.updateLock(authUserDTO);
	}



}
