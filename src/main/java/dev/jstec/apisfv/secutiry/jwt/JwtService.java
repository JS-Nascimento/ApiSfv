package dev.jstec.apisfv.secutiry.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import dev.jstec.apisfv.ApisfvApplication;
import dev.jstec.apisfv.domain.entity.UserLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	
	@Value("${security.jwt.expiration}")
	private String expiration;
	
	@Value("${security.jwt.signKey}")
	private String signKey;
	
	
	public String generateToken(UserLogin userLogin) {
		
		Long expirationString = Long.valueOf(expiration);
		LocalDateTime dateAndTimeExpiration = LocalDateTime.now().plusMinutes(expirationString);
		Instant instant = dateAndTimeExpiration.atZone(ZoneId.systemDefault()).toInstant();
		Date dateExpiration = Date.from(instant);
		
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("userEmail", "usuario@email.com");
		claims.put("roles", "admin");
		
		return Jwts
				.builder()
				.setSubject(userLogin.getLogin())
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS512, signKey)
				.compact();
		
	}
	
	
	
	private Claims getClaims(String token) throws ExpiredJwtException {
		
		return Jwts
					.parser()
					.setSigningKey(signKey)
					.parseClaimsJws(token)
					.getBody();
	}
	
	public boolean validateToken(String token) {
		try {
			
			Claims claims = getClaims(token);
			Date expirationDate = claims.getExpiration();
			LocalDateTime expDate =  expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			
			return !LocalDateTime.now().isAfter(expDate);
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public String getUserLogin(String token) throws ExpiredJwtException {
		return (String) getClaims(token).getSubject();
	}
	
	
	
	public static void main(String[] args) {
		
		
		ConfigurableApplicationContext contexto = SpringApplication.run(ApisfvApplication.class) ;
				JwtService service = contexto.getBean(JwtService.class);
			UserLogin userLogin = UserLogin.builder().login("Fulano").build();
			String token = service.generateToken(userLogin);
			System.out.println(token);
			
			boolean isTokenValido = service.validateToken(token);
			System.out.println("o token esta valido ? " + isTokenValido);
			
			System.out.println(service.getUserLogin(token));
		
	}
}
