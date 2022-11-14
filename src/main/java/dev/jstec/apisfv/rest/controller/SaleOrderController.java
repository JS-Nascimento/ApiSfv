package dev.jstec.apisfv.rest.controller;

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jstec.apisfv.domain.entity.SaleOrder;
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
	
	
	
	
		
}
