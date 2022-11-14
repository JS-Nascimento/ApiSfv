package dev.jstec.apisfv.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.jstec.apisfv.domain.entity.Client;


public interface Clients extends JpaRepository<Client, Integer> {
	
	List<Client> findByNameLike(String name);
	
	
	
	@Query("select c from  Client c left join fetch c.order where c.id = :id ")
	Client findClientFetchSaleOrder( @Param("id") Integer id );
}

