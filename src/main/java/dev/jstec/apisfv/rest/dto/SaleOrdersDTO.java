package dev.jstec.apisfv.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrdersDTO {
	private  Integer client_Id;
	private BigDecimal total;
	private List<OrderItemDTO> items;
	
	

}
