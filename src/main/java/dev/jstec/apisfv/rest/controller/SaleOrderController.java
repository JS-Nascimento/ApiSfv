package dev.jstec.apisfv.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;

import dev.jstec.apisfv.domain.entity.OrderItem;
import dev.jstec.apisfv.domain.entity.SaleOrder;
import dev.jstec.apisfv.domain.enums.OrderStatus;
import dev.jstec.apisfv.rest.dto.OrderItemInfoDTO;
import dev.jstec.apisfv.rest.dto.SaleOrderInfoDTO;
import dev.jstec.apisfv.rest.dto.SaleOrderStatusUpdateDTO;
import dev.jstec.apisfv.rest.dto.SaleOrdersDTO;
import dev.jstec.apisfv.services.SaleOrderService;

@RestController
@RequestMapping("/api/saleorders")
public class SaleOrderController {

	private SaleOrderService service;

	public SaleOrderController(SaleOrderService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Integer save(@RequestBody SaleOrdersDTO dto) {
		
		SaleOrder sOrder = service.save(dto);
		return sOrder.getId();
			
	}
	
	@GetMapping("{id}")
	public SaleOrderInfoDTO getById(@PathVariable Integer id) {
		return service
					.getOrderInfo(id)
					.map( p -> transformOrder(p))
					.orElseThrow( () -> new ResponseStatusException(NOT_FOUND, "Order no has exist!" ) );
		
	}
	
	private SaleOrderInfoDTO transformOrder(SaleOrder order) {
		
	return	SaleOrderInfoDTO
					.builder()
					.id(order.getId())
					.orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
					.cpf(order.getClient().getCpf())
					.name(order.getClient().getName())
					.total(order.getTotal())
					.status(order.getStatus().name())
					.items(transformOrderItems(order.getItems()))
					.build();
}
	
	
	@PatchMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void statusUpdate(@PathVariable Integer id, 
								@RequestBody SaleOrderStatusUpdateDTO dto) {
		
		String newStatus = dto.getNewStatus();
		service.statusUpdate(id, OrderStatus.valueOf(newStatus));
		
	}

	private List<OrderItemInfoDTO> transformOrderItems(List<OrderItem> items) {
		if(CollectionUtils.isEmpty(items)) {
			return Collections.emptyList();
		}
		return items.stream()
				.map(item -> OrderItemInfoDTO
						.builder()
						.description(item.getProduct().getDescription())
						.price(item.getProduct().getPrice())
						.quantity(item.getQuantity())
						.build()
						).collect(Collectors.toList()) ;
	}}
	
	
