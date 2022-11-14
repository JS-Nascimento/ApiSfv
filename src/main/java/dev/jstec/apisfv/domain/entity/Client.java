package dev.jstec.apisfv.domain.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column(name="name", length =100)
	@NotEmpty(message="Client name cannot be empty!")
	private String name;
	
	@Column(name ="cpf", length = 11)
	@NotEmpty(message="Client CPF cannot be empty!")
	@CPF(message = "Client CPF is not valid!")
	private String cpf;
	
	public Client(Integer id, String name) {
		
		this.id = id;
		this.name = name;
	}
	

	 	@JsonIgnore
	 	@OneToMany( mappedBy = "client" , fetch = FetchType.LAZY )
	    private Set<SaleOrder> order;
	
	   	    
	

}
