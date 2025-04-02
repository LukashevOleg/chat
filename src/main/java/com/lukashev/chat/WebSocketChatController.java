package com.lukashev.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WebSocketChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat") // Клиент отправляет сюда
    @SendTo("/topic/messages") // Сообщение рассылается подписчикам
    public Map<String, String> handleChatMessage(@AuthenticationPrincipal Jwt jwt, Map<String, String> message) {
//        String username = jwt.getClaimAsString("preferred_username");
        System.out.println(message);
        return Map.of(
                "from", message.get("from"),
                "text", message.get("text"),
                "time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        );
    }
}
