package dev.jstec.apisfv.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jstec.apisfv.domain.entity.Product;

public interface Products extends JpaRepository<Product, Integer>{
	
	
	

}
