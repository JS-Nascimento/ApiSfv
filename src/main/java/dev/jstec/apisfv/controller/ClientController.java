package dev.jstec.apisfv.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.jstec.apisfv.domain.entity.Client;
import dev.jstec.apisfv.domain.repository.Clients;

@Controller
public class ClientController {

	private Clients clients;

	public ClientController(Clients clients) {

		this.clients = clients;
	}

	@GetMapping("/api/clients/{id}")
	@ResponseBody
	public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
		Optional<Client> client = clients.findById(id);
		if (client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping("/api/clients")
	@ResponseBody
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		Client savedClient = clients.save(client);
		return ResponseEntity.ok(savedClient);
	}

	@DeleteMapping("/api/clients/{id}")
	@ResponseBody
	public ResponseEntity<Client> delete(@PathVariable Integer id) {
		Optional<Client> client = clients.findById(id);
		
		if (client.isPresent()) {
			clients.delete(client.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();

	}
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/api/clients/{id}")
	@ResponseBody
	public ResponseEntity updateClient(@PathVariable Integer id, @RequestBody Client client) {
		
		return clients
				.findById(id)
				.map( existingClient -> {
					client.setId(existingClient.getId());
					clients.save(client);
					return ResponseEntity.noContent().build();
				}).orElseGet( () -> ResponseEntity.notFound().build());
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/api/clients")
	public ResponseEntity findClient(Client filteredClient) {
		
		ExampleMatcher matcher = ExampleMatcher
								.matching()
								.withIgnoreCase()
								.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<Client> example = Example.of(filteredClient, matcher)	;	
		List<Client> clientList = clients.findAll(example);
		
		return ResponseEntity.ok(clientList);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}