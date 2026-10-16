package egovframework.nicc.hne.websocket;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.web.socket.WebSocketSession;

public class SubProtocolWebSocketHandlerInterceptor extends DelegatingIntroductionInterceptor {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected Object doProceed(MethodInvocation mi) throws Throwable {
		if(mi.getMethod().getName().equals("afterConnectionEstablished")){
			WebSocketSession session = (WebSocketSession) mi.getArguments()[0];
			session.setTextMessageSizeLimit(150 * 1024 * 1024);
			System.out.println("session.getTextMessageSizeLimit : " + session.getTextMessageSizeLimit());
		}
		return super.doProceed(mi);
	}
}
