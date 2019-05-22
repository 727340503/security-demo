package com.imooc.security.browser.permission;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("rbacPermission")
public class RbacPermission {

//    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if(principal instanceof UserDetails) {
        	String username = ((UserDetails) principal).getUsername();
        	System.out.println("自定义鉴权User name:"+username);
//        	hasPermission = true;
        	
           /* String username = ((UserDetails) principal).getUsername();
            // 读取用户所拥有的权限url，这里应该从数据库获取
            Set<String> urls = new HashSet<>();

            for (String url : urls) {
                // 判断当前url是否有权限
                if(antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }*/
        }
        return hasPermission;
    }
}