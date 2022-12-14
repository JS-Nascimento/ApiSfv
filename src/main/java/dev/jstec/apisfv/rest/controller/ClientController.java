package dev.jstec.apisfv.rest.controller;

import java.util.List;

import javax.validation.Valid;

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

import dev.jstec.apisfv.domain.entity.Client;
import dev.jstec.apisfv.domain.repository.Clients;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/clients")
@Api("Api Clientes")
public class ClientController {

	private Clients clients;

	public ClientController(Clients clients) {

		this.clients = clients;
	}

	@GetMapping("{id}")
	@ApiOperation("Obter detalhes de um Cliente")
	@ApiResponses({
		@ApiResponse(code = 200, message ="Requisição bem sucedida"),
		@ApiResponse(code = 404, message ="Requisição não encontrada")
	})
	public Client getClientById(@PathVariable 
									@ApiParam("ID do Cliente") Integer id) {
		
		return clients
				.findById(id)
				.orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
		
		 

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Persisti novo Cliente")
	@ApiResponses({
		@ApiResponse(code = 201, message ="Requisição criada com sucesso"),
		@ApiResponse(code = 404, message ="Requisição não encontrada")
	})
	public Client saveClient(@RequestBody @Valid Client client) {
		 
		return clients.save(client);
	}
	

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable Integer id) {
		
		clients
		.findById(id)
			.map( client -> { clients.delete(client); 
				return client;
			})
			.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
	}
	
	
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Client updateClient(@PathVariable Integer id, @RequestBody @Valid Client client) {
		
		return clients
				.findById(id)
				.map( existingClient -> {
					client.setId(existingClient.getId());
					clients.save(client);
					return existingClient;
				}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
		
	}
	

	@GetMapping
	public List<Client> findClient(Client filteredClient) {
		
		ExampleMatcher matcher = ExampleMatcher
								.matching()
								.withIgnoreCase()
								.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<Client> example = Example.of(filteredClient, matcher)	;	
		return clients.findAll(example);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}