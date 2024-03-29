package com.chordplay.chordplayapiserver.global.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// class 출처 : https://offbyone.tistory.com/144

public class ContextUtil {
    /**
     * 빈을 직접 얻습니다.
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        return context.getBean(beanName);
    }

    /**
     * HttpServletReqeust 객체를 직접 얻습니다.
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attr =
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    /**
     * HttpServletResponse 객체를 직접 얻습니다.
     * @return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attr =
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }


    /**
     * HttpSession 객체를 직접 얻습니다.
     *
     * @param gen 새 세션 생성 여부
     * @return
     */
    public static HttpSession getSession(boolean gen) {
        return ContextUtil.getRequest().getSession(gen);
    }

    /**
     * REQUEST 영역에서 가져오기
     *
     * @param key
     * @return
     */
    public static Object getAttrFromRequest(String key) {
        ServletRequestAttributes attr =
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getAttribute(key, ServletRequestAttributes.SCOPE_REQUEST);
    }

    /**
     * REQUEST 영역에 객체 저장
     *
     * @param key
     * @param obj
     */
    public static void setAttrToRequest(String key, Object obj) {
        ServletRequestAttributes attr =
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        attr.setAttribute(key, obj, ServletRequestAttributes.SCOPE_REQUEST);
    }

    /**
     * SESSION 영역에서 가져오기
     *
     * @param key
     * @return
     */
    public static Object getAttrFromSession(String key) {
        ServletRequestAttributes attr =
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getAttribute(key, ServletRequestAttributes.SCOPE_SESSION);
    }

    /**
     * Session 영역에 객체 저장
     *
     * @param key
     * @param obj
     */
    public static void setAttrToSession(String key, Object obj) {
        ServletRequestAttributes attr =
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        attr.setAttribute(key, obj, ServletRequestAttributes.SCOPE_SESSION);
    }

    public static String getPrincipalUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails userDetails = (PrincipalDetails)principal;
        String userId = userDetails.getUser().getId();
        return userId;
    }
}
