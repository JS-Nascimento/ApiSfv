package dev.jstec.apisfv.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jstec.apisfv.domain.entity.UserLogin;

public interface Users extends JpaRepository<UserLogin,Integer> {
	
	
	Optional<UserLogin> findByLogin(String login);

	

}
