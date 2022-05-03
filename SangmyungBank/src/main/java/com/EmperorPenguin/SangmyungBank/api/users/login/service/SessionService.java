package com.EmperorPenguin.SangmyungBank.api.users.login.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionService {

    public static final String SESSION_COOKIE_Name = "_sid";

    private Map<String,Object> sessionStore = new ConcurrentHashMap<>();

    public void CreateSession(Object value, HttpServletResponse response){
        String sessionId = "SB" + UUID.randomUUID().toString();
        sessionStore.put(sessionId,value);
        Cookie SessionCookieId = new Cookie(SESSION_COOKIE_Name, sessionId);
        response.addCookie(SessionCookieId);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    /*
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookieId = findCookie(request, SESSION_COOKIE_Name);
        if (sessionCookieId == null ) {
            return null;
        }
        return sessionStore.get(sessionCookieId.getValue());
    }
    /*
     * 세션 만료
     */
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_Name);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }

    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }
}
