package dev.jstec.apisfv.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jstec.apisfv.domain.entity.OrderItem;

public interface OrderItems extends JpaRepository<OrderItem, Integer> {

}
