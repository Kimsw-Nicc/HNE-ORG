package egovframework.nicc.hne.websocket;

import java.util.List;

import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.MessageConverter;
//import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
//import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
//import org.springframework.messaging.simp.config.ChannelRegistration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app"); // 도착 경로 설정 (/app/**)
		registry.enableSimpleBroker("/topic", "/queue"); // topic 전체, queue단일
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket").setAllowedOrigins("*").addInterceptors(new HttpHandshakeInterceptor())
		.withSockJS().setHeartbeatTime(1000);
		registry.addEndpoint("/websocket").setAllowedOrigins("*").addInterceptors(new HttpHandshakeInterceptor());
//		registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
//		registry.addEndpoint("/websocket");
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		registry.setMessageSizeLimit(100 * 1024 * 1024); // Max incoming message size, default : 64 * 1024
		registry.setSendTimeLimit(200 * 10000); // default : 10 * 10000
		registry.setSendBufferSizeLimit(100 * 1024 * 1024); // Max outgoing buffer size, default : 512 * 1024
		
//		AgentWebSocketHandlerDecoratorFactory agent = new AgentWebSocketHandlerDecoratorFactory();
//		registry.setDecoratorFactories(agent);
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {

	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		return true;
	}
}