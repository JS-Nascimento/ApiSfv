package dev.jstec.apisfv.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.jstec.apisfv.domain.entity.UserLogin;
import dev.jstec.apisfv.exception.InvalidPasswordException;
import dev.jstec.apisfv.rest.dto.CredentialsDTO;
import dev.jstec.apisfv.rest.dto.TokenDTO;
import dev.jstec.apisfv.secutiry.jwt.JwtService;
import dev.jstec.apisfv.services.implementation.UserServiceImplementation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserLoginController {
	
	    private final UserServiceImplementation userService;
	    private final PasswordEncoder passwordEncoder;
	    private final JwtService jwtService;

	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public UserLogin save( @RequestBody @Valid UserLogin userLogin ){
	        String passCript = passwordEncoder.encode(userLogin.getPassword());
	        userLogin.setPassword(passCript);
	        return userService.saveUser(userLogin);
	    }
	    
	    @PostMapping("/auth")
	    public TokenDTO authenticate (@RequestBody CredentialsDTO credentials) {
	    	
	    	try {
	    		
	    		UserLogin userLogin = UserLogin.builder().login(credentials.getLogin())
						.password(credentials.getPassword()).build();
	    		
	    		UserDetails userAuthenticated = userService.authenticate(userLogin);
	    		String token = jwtService.generateToken(userLogin);
	    		
	    		return new TokenDTO(userLogin.getLogin(), token);
				
	    		
			} catch (UsernameNotFoundException | InvalidPasswordException e) {
				
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
				    	
	    	}
	    	
	    }
	    
	    
	}


