package dev.jstec.apisfv.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleOrderInfoDTO {
	
	private Integer id;
	private String cpf;
	private String name;
	private String orderDate;
	private BigDecimal total;
	private String status;
	private List<OrderItemInfoDTO> items;
	
	

}
