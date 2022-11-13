package dev.jstec.apisfv.domain.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	public Client() {
		
	}
	
	public Client(String name) {
		
		this.name = name;
	}
	
		

	public Client(Integer id, String name) {
		
		this.id = id;
		this.name = name;
	}
	
	
	 	@JsonIgnore
	 	@OneToMany( mappedBy = "client" , fetch = FetchType.LAZY )
	    private Set<SaleOrder> order;

	   

	    public Set<SaleOrder> getOrders() {
	        return order;
	    }

	    public void setOrders(Set<SaleOrder> order) {
	        this.order = order;
	    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}
	
}
