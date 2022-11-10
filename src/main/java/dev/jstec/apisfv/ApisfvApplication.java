package dev.jstec.apisfv;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.jstec.apisfv.domain.entity.Client;
import dev.jstec.apisfv.domain.repository.Clients;




@SpringBootApplication

public class ApisfvApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clients clients) {
		
		return args -> {
			clients.save(new Client("Jorge Nascimento"));
			clients.save(new Client("Danusa Mauricio"));
			
			List<Client> allClients = clients.findAll();
			allClients.forEach(System.out::println);
			
			System.out.println("Atualizando");
			allClients.forEach(c -> { c.setName(c.getName() + "Updated");
			clients.save(c);
			});
			
			allClients = clients.findAll();
			allClients.forEach(System.out::println);
			
			System.out.println("Find Client per Name");
			clients.findByNameLike("Jorg").forEach(System.out::println);
			
			System.out.println("Deletando");
			clients.findAll().forEach(c -> {
				clients.delete(c);
			});
			
			allClients = clients.findAll();
			if (allClients.isEmpty()) {
				System.out.println("nenhum cliente encontrado");
			}else {
				allClients.forEach(System.out::println);
			}
			
		};
		
	}
	

	public static void main(String[] args) {
		SpringApplication.run(ApisfvApplication.class, args);
		
	}

}
