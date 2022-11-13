package dev.jstec.apisfv;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication

public class ApisfvApplication {
	
//	@Bean
//	public CommandLineRunner init(@Autowired Clients clients, @Autowired SaleOrders saleOrders) {
//		
//		return args -> {
//			System.out.println("Salvando Cliente");
//			Client fulano = new Client("Fulano");
//			clients.save(fulano);
//			
			
			
			
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
//		};
		
//	}
	

	public static void main(String[] args) {
		SpringApplication.run(ApisfvApplication.class, args);
		
	}

}
