package dev.jstec.apisfv.services;

import dev.jstec.apisfv.domain.entity.SaleOrder;
import dev.jstec.apisfv.rest.dto.SaleOrdersDTO;

public interface SaleOrderService {

		SaleOrder save (SaleOrdersDTO dto);
}
