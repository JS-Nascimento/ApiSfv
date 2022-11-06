package dev.jstec.apisfv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.jstec.apisfv.model.Client;
import dev.jstec.apisfv.repository.ClientRepository;

@Service
public class ClientService {
	
	private ClientRepository repository;
	
	@Autowired
	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}

	public void saveClient(Client client) {
		ClientValidate(client);
		this.repository.Persist(client);
	}
	
	public void ClientValidate(Client client) {
		//aplicar regras de negocio
	}
	
}
