package egovframework.nicc.hne.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGIN = "login";

	@Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();
    if (session.getAttribute(LOGIN) == null) {
      response.sendRedirect("/");
      return false; // 요청을 계속 처리하지 않음
    }
    return true; // 로그인 정보가 있는 경우 요청을 계속 처리
  }
}
