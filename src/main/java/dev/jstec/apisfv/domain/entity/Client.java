package dev.jstec.apisfv.domain.entity;

import java.util.Set;

import javax.persistence.*;

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
	private String name;
	
	@Column(name ="cpf", length = 11)
	private String cpf;
	
	public Client(Integer id, String name) {
		
		this.id = id;
		this.name = name;
	}
	

	 	@JsonIgnore
	 	@OneToMany( mappedBy = "client" , fetch = FetchType.LAZY )
	    private Set<SaleOrder> order;
	
	   	    
	

}
