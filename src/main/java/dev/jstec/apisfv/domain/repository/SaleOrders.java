package dev.jstec.apisfv.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jstec.apisfv.domain.entity.SaleOrder;

public interface SaleOrders extends JpaRepository<SaleOrder, Integer>{

}
