package dev.jstec.apisfv.secutiry.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.jstec.apisfv.services.implementation.UserServiceImplementation;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private JwtService jwtservice;
	private UserServiceImplementation userService;
	
	

	public JwtAuthFilter(JwtService jwtservice, UserServiceImplementation userService) {
		this.jwtservice = jwtservice;
		this.userService = userService;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
			
			String authorization =  request.getHeader("Authorization");
			if(authorization != null && authorization.startsWith("Bearer")) {
				String token = authorization.split(" ")[1];
				
				boolean isValid = jwtservice.validateToken(token);
				
				if(isValid) {
					String userLogin = jwtservice.getUserLogin(token);
					UserDetails userDetails = userService.loadUserByUsername(userLogin);
					UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails,null, 
							userDetails.getAuthorities());
					
					user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(user);
				}
			}
			
			filterChain.doFilter(request, response);

	}		
}
