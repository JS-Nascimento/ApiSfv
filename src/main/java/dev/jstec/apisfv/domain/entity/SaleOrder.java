package dev.jstec.apisfv.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;



@Entity
@Table
public class SaleOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "Client_Id")
	private Client client;
	
	@Column(name = "OrderDate")
	private LocalDate orderDate;
	
	
	private BigDecimal total;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	

}
