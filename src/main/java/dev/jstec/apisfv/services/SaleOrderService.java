package dev.jstec.apisfv.services;

import java.util.Optional;

import dev.jstec.apisfv.domain.entity.SaleOrder;
import dev.jstec.apisfv.domain.enums.OrderStatus;
import dev.jstec.apisfv.rest.dto.SaleOrdersDTO;

public interface SaleOrderService {

		SaleOrder save (SaleOrdersDTO dto);
		
		Optional <SaleOrder> getOrderInfo(Integer id);
		
		void statusUpdate(Integer id, OrderStatus statusPedido );
}
