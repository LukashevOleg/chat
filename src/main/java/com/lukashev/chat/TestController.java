package com.lukashev.chat;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }

    @GetMapping("/protected")
//    @PreAuthorize("isAuthenticated()")
    public String protectedEndpoint(@AuthenticationPrincipal Jwt user) {
        System.out.println("protectedEndpoint");
        return "Hello, "+ user.getClaim("preferred_username")  + "! This is a protected endpoint";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OidcUser user) {
        return "Hello, " + user.getFullName() + "! This is a home page";
    }

//    @GetMapping("/protected")
//    public String protectedEndpoint(
//            @AuthenticationPrincipal Jwt jwt,
//            HttpServletRequest request
//    ) {
//        // Логируем все заголовки
//        System.out.println("=== HEADERS ===");
//        Collections.list(request.getHeaderNames())
//                .forEach(header -> System.out.println(header + ": " + request.getHeader(header)));
//
//        // Логируем JWT (если есть)
//        if (jwt != null) {
//            System.out.println("=== JWT CLAIMS ===");
//            jwt.getClaims().forEach((k, v) -> System.out.println(k + ": " + v));
//        } else {
//            System.out.println("JWT is NULL");
//        }
//
//        return "Check console logs";
//    }
}
