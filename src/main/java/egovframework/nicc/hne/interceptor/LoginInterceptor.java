package egovframework.nicc.hne.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.nicc.hne.auth.model.dto.AuthUserDTO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 기존 login session 존재시 삭제해버림
		HttpSession session = request.getSession();

		if (session.getAttribute(LOGIN) != null) {
			session.removeAttribute(LOGIN);
		}
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		AuthUserDTO authUserDTO = (AuthUserDTO) session.getAttribute(LOGIN);
		if (authUserDTO != null) {
			// 로그인 후 추가 처리 (예: 로깅)
		}
	}

}

