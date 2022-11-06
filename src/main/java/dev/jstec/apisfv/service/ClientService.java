package dev.jstec.apisfv.service;

import org.springframework.stereotype.Service;

import dev.jstec.apisfv.model.Client;
import dev.jstec.apisfv.repository.ClientRepository;

@Service
public class ClientService {

	public void saveClient(Client client) {
		ClientValidate(client);
		ClientRepository clientRepository = new ClientRepository();
		clientRepository.Persist(client);
	}
	
	public void ClientValidate(Client client) {
		//aplicar regras de negocio
	}
	
}
