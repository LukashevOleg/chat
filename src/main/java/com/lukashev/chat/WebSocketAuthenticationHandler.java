package com.lukashev.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthenticationHandler implements ChannelInterceptor {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = (String) accessor.getSessionAttributes().get("token");

            if (token != null) {
                Jwt jwt = jwtDecoder.decode(token);
                Authentication auth = new JwtAuthenticationToken(jwt);
                accessor.setUser(auth); // Устанавливаем пользователя в сессию
            }
        }

        return message;
    }
}
