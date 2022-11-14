package dev.jstec.apisfv.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jstec.apisfv.domain.entity.Client;
import dev.jstec.apisfv.domain.entity.SaleOrder;

public interface SaleOrders extends JpaRepository<SaleOrder, Integer>{
	
	List<SaleOrder> findByClient(Client client);

}
	