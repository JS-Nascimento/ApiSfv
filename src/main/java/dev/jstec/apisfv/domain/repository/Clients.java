package dev.jstec.apisfv.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.jstec.apisfv.domain.entity.Client;

@Repository
public interface Clients extends JpaRepository<Client, Integer> {
	
	List<Client> findByNameLike(String name);
}

