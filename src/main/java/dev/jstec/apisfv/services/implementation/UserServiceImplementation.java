package dev.jstec.apisfv.services.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.jstec.apisfv.domain.entity.UserLogin;
import dev.jstec.apisfv.domain.repository.Users;
import dev.jstec.apisfv.exception.InvalidPasswordException;


@Service
public class UserServiceImplementation implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Users userRepository;
	
	 	@Transactional
	    public UserLogin saveUser(UserLogin userLogin){
	        return userRepository.save(userLogin);
	    }
	

	 public UserDetails autenticar( UserLogin usuario ){
	         UserDetails user = loadUserByUsername(usuario.getLogin());
	         boolean senhasBatem = encoder.matches( usuario.getPassword(), user.getPassword() );

	         if(senhasBatem){
	             return user;
	         }

	         throw new InvalidPasswordException();
	     }
 	
	 	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserLogin userLogin = userRepository.findByLogin(username)
			.orElseThrow( () -> new UsernameNotFoundException("Usuario nao encotrado"));
		
		String[] roles = userLogin.isAdmin() ? new String[] {"USER", "ADMIN"} : new String[] {"USER"};
		
		return User
				.builder()
				.username(userLogin.getLogin())
				.password(userLogin.getPassword())
				.roles(roles)
				.build();
	}

}
