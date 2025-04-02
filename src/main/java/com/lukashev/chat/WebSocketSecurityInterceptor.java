package com.lukashev.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class WebSocketSecurityInterceptor implements HandshakeInterceptor {

    private final JwtDecoder jwtDecoder;


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {

        if (request instanceof ServletServerHttpRequest servletRequest) {
            String authHeader = servletRequest.getServletRequest().getHeader("Authorization");
            System.out.println("=============================");
            System.out.println(request.getURI().getPath());
            System.out.println("=============================");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                attributes.put("token", token); // Сохраняем токен в атрибутах сессии
            }
        }

        return true;

//        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            String authHeader = servletRequest.getServletRequest().getHeader("Authorization");
//
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                String token = authHeader.substring(7);
//                try {
//                    Jwt jwt = jwtDecoder.decode(token);
//                    Authentication authentication = new JwtAuthenticationToken(jwt);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    attributes.put("jwt", jwt); // Передаем JWT в WebSocket сессию
//                    return true;
//                } catch (JwtException e) {
//                    return false; // Неверный токен, отклоняем соединение
//                }
//            }
//        }
//        return false;



    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {

        System.out.println("-----------------------------------");

        // Можно не реализовывать
    }
}
