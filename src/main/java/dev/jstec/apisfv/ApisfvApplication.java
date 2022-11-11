package dev.jstec.apisfv;




import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.jstec.apisfv.domain.entity.Client;
import dev.jstec.apisfv.domain.entity.SaleOrder;
import dev.jstec.apisfv.domain.repository.Clients;
import dev.jstec.apisfv.domain.repository.SaleOrders;




@SpringBootApplication

public class ApisfvApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clients clients, @Autowired SaleOrders saleOrders) {
		
		return args -> {
			System.out.println("Salvando Cliente");
			Client fulano = new Client("Fulano");
			clients.save(fulano);
			
			SaleOrder o = new SaleOrder();
			
			o.setClient(fulano);
			o.setOrderDate(LocalDate.now());
			o.setTotal(BigDecimal.valueOf(11000));
			
			saleOrders.save(o);
			
			Client purchaseClient = clients.findClientFetchSaleOrder(fulano.getId());
			System.out.println(purchaseClient);
			System.out.println(purchaseClient.getOrders());
			
			
			/*
			 * List<Client> allClients = clients.findAll();
			 * allClients.forEach(System.out::println);
			 * 
			 * System.out.println("Atualizando"); allClients.forEach(c -> {
			 * c.setName(c.getName() + "Updated"); clients.save(c); });
			 * 
			 * allClients = clients.findAll(); allClients.forEach(System.out::println);
			 * 
			 * System.out.println("Find Client per Name");
			 * clients.findByNameLike("Jorg").forEach(System.out::println);
			 * 
			 * System.out.println("Deletando"); clients.findAll().forEach(c -> {
			 * clients.delete(c); });
			 * 
			 * allClients = clients.findAll(); if (allClients.isEmpty()) {
			 * System.out.println("nenhum cliente encontrado"); }else {
			 * allClients.forEach(System.out::println); }
			 */
		};
		
	}
	

	public static void main(String[] args) {
		SpringApplication.run(ApisfvApplication.class, args);
		
	}

}
