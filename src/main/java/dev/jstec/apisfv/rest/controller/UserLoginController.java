package dev.jstec.apisfv.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jstec.apisfv.domain.entity.UserLogin;
import dev.jstec.apisfv.services.implementation.UserServiceImplementation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserLoginController {
	
	    private final UserServiceImplementation userService;
	    private final PasswordEncoder passwordEncoder;

	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public UserLogin save( @RequestBody @Valid UserLogin userLogin ){
	        String passCript = passwordEncoder.encode(userLogin.getPassword());
	        userLogin.setPassword(passCript);
	        return userService.saveUser(userLogin);
	    }
	}


