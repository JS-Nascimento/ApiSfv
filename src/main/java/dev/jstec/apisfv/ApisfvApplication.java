package dev.jstec.apisfv;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApisfvApplication {
	
	
	
	@GetMapping("/appName")
	public String appName() {
		return "Sales Force API";
	}
	public static void main(String[] args) {
		SpringApplication.run(ApisfvApplication.class, args);
		
	}

}
