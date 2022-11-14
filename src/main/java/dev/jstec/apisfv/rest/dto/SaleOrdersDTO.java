package dev.jstec.apisfv.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import dev.jstec.apisfv.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrdersDTO {
	
	
	@NotNull(message="Client is required!")
	private  Integer client_Id;
	
	@NotNull(message="Total is required!")
	private BigDecimal total;
	
	@NotEmptyList
	private List<OrderItemDTO> items;
	
	

}
