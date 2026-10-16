package egovframework.nicc.hne.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class WebSocketEventListener extends ChannelInterceptorAdapter {

	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
	}

	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {
		System.out.println("getReason : "+event.getCloseStatus().getReason());
		System.out.println("getCode : "+event.getCloseStatus().getCode());
//	System.out.println(new String(event.getMessage().getPayload()));

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String session_id = headerAccessor.getSessionId();
    System.out.println("Disconnected session ID: " + session_id);
		try {
//		controller.disConnect(session_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@EventListener
	private void handleSessionSubscribe(SessionSubscribeEvent event) {
	}

	@EventListener
	private void handleSessionUnsubscribe(SessionUnsubscribeEvent event) {
	}
}
