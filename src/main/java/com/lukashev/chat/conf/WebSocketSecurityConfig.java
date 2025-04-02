package com.lukashev.chat.conf;

import com.lukashev.chat.WebSocketAuthenticationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;

@Configuration
@RequiredArgsConstructor
//@EnableWebSocketSecurity
public class WebSocketSecurityConfig {

//    private final JwtDecoder jwtDecoder;
//
//    @Bean
//    AuthorizationManager<Message<?>> authorizationManager(MessageMatcherDelegatingAuthorizationManager.Builder messages) {
//        System.out.println("+++++++++++++++++++++");
//                messages.anyMessage().authenticated();
//                return messages.build();
//        }
//
//    @Bean
//    public ChannelInterceptor jwtChannelInterceptor() {
//        return new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
//                    String token = accessor.getFirstNativeHeader("Authorization");
//                    if (token != null && token.startsWith("Bearer ")) {
//                        token = token.substring(7);
//                        Jwt jwt = jwtDecoder.decode(token);
//                        Authentication authentication = new JwtAuthenticationToken(jwt);
//                        accessor.setUser(authentication);
//                    }
//                }
//                return message;
//            }
//        };
//    }

//    @Bean
//    public void configureInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(jwtChannelInterceptor());
//    }
}
