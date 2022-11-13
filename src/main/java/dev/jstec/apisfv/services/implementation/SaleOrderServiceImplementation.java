package dev.jstec.apisfv.services.implementation;

import org.springframework.stereotype.Service;

import dev.jstec.apisfv.domain.repository.SaleOrders;
import dev.jstec.apisfv.services.SaleOrderService;

@Service
public class SaleOrderServiceImplementation implements SaleOrderService {
	
	private SaleOrders saleOrders;

	public SaleOrderServiceImplementation(SaleOrders saleOrders) {
		
		this.saleOrders = saleOrders;
	}
	
	

}
