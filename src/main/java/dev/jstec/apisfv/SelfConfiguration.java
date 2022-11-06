package dev.jstec.apisfv;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelfConfiguration {
	
	@Bean(name="applicationName")
	public String applicationName() {
		return "Sales Force Api";
	}
	
}
