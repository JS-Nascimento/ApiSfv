package dev.jstec.apisfv.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import dev.jstec.apisfv.domain.entity.Product;
import dev.jstec.apisfv.domain.repository.Products;

@RestController
@RequestMapping("api/products")
public class ProductController {

	private Products products;

	public ProductController(Products products) {
		
		this.products = products;
	}
	
	@GetMapping("{id}")
	public Product getClientById(@PathVariable Integer id) {
		
		return products
				.findById(id)
				.orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
		
		 

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product saveProduct(@RequestBody Product product) {
		 
		return products.save(product);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable Integer id) {
		
		products
		.findById(id)
			.map( product -> { products.delete(product); 
				return product;
			})
			.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found!"));
	}
	
	
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Product updateproduct(@PathVariable Integer id, @RequestBody Product product) {
		
		return products
				.findById(id)
				.map( existingproduct -> {
					product.setId(existingproduct.getId());
					products.save(product);
					return existingproduct;
				}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found!"));
		
	}
	
	@GetMapping
	public List<Product> findproduct(Product filteredproduct) {
		
		ExampleMatcher matcher = ExampleMatcher
								.matching()
								.withIgnoreCase()
								.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<Product> example = Example.of(filteredproduct, matcher)	;	
		return products.findAll(example);
		
		
		
	}
	
}
