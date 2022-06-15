package com.tameme.cobranza.front;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String url = null;
		
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
		for(GrantedAuthority ga: auth) {
			if (ga.getAuthority().equals("ROLE_USER")) {
				url = "user/index";
				break;
			} else if (ga.getAuthority().equals("ROLE_ADMIN")) {
				url = "admin/index";
				break;
			} else {
				url = "customer/index";
				break;				
			}
		}
		
		if (url == null)
			throw new IllegalStateException();
		
		new DefaultRedirectStrategy().sendRedirect(request, response, url);
		
	}

}
