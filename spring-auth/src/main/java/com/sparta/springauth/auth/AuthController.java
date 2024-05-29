package com.sparta.springauth.auth;

import com.sparta.springauth.entity.UserRoleEnum;
import com.sparta.springauth.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/create-cookie")
    public String createCookie(HttpServletResponse res) {
        addCookie("Robbie Auth", res);

        return "createCookie";
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value) {
        System.out.println("value = " + value);

        return "getCookie: " + value;
    }

    @GetMapping("/create-session")
    public String createSession(HttpServletRequest request) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 새로 생성해서 반환
        HttpSession session = request.getSession(true);
        //세션에 저장될 정보 Name:Value를 추가
        session.setAttribute(AUTHORIZATION_HEADER, "Robbie Auth");
        return "createSession";
    }

    @GetMapping("/get-session")
    public String getSession(HttpServletRequest request) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 null 반환
        HttpSession session = request.getSession(false);

        String value = (String) session.getAttribute(AUTHORIZATION_HEADER);
        System.out.println("value = " + value);

        return "getSession : " + value;
    }

    @GetMapping("create-jwt")
    public String createJwt(HttpServletResponse response) {
        String token = jwtUtil.createToken("Robbie", UserRoleEnum.USER);

        jwtUtil.addJwtToCookie(token, response);

        return "createJwt: " + token;
    }

    @GetMapping("/get-jwt")
    public String getJwt(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        String token = jwtUtil.substringToken(tokenValue);

        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token);
        String userName = info.getSubject();
        System.out.println("userName = " + userName);
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        System.out.println("authority = " + authority);

        return "getJwt: " + userName + ", " + authority;

    }

    public static void addCookie(String cookieValue, HttpServletResponse response) {
        try {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8").replaceAll("\\+", "%20"); // Cookie에는 공백 사용 불가능해서 encode 해야함

            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, cookieValue);
            cookie.setPath("/");
            cookie.setMaxAge(30 * 60);

            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
