package egovframework.nicc.hne.auth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;
import egovframework.nicc.hne.auth.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String goLogin(HttpSession session) throws Exception {
		if (session != null) {
			session.invalidate();
		}
		return "login";
	}

	@ResponseBody
	@PostMapping("/login")
	public String doLogin(@RequestBody AuthUserDTO authUserDTO, HttpSession session) {
		String msg = userService.checkLogin(authUserDTO);
		if ("SUCCESS".equals(msg)) {
			AuthUserDTO user = userService.getUserByUserId(authUserDTO.getUserId());
			session.setAttribute("login", user);
		}
		return msg;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/"; // 로그아웃 후 리디렉트할 페이지로 이동
	}

	@GetMapping("/rest/isConn")
	public void restIsConn() {
	}
}
