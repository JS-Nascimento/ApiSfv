package dev.jstec.apisfv.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.jstec.apisfv.domain.entity.Client;
import dev.jstec.apisfv.domain.entity.OrderItem;
import dev.jstec.apisfv.domain.entity.Product;
import dev.jstec.apisfv.domain.entity.SaleOrder;
import dev.jstec.apisfv.domain.enums.OrderStatus;
import dev.jstec.apisfv.domain.repository.Clients;
import dev.jstec.apisfv.domain.repository.OrderItems;
import dev.jstec.apisfv.domain.repository.Products;
import dev.jstec.apisfv.domain.repository.SaleOrders;
import dev.jstec.apisfv.exception.BusinessRoleException;
import dev.jstec.apisfv.exception.OrderNotFoundException;
import dev.jstec.apisfv.rest.dto.OrderItemDTO;
import dev.jstec.apisfv.rest.dto.SaleOrdersDTO;
import dev.jstec.apisfv.services.SaleOrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleOrderServiceImplementation implements SaleOrderService {

	private final SaleOrders repository;
	private final Clients clientsRepository;
	private final Products productsRepository;
	private final OrderItems itemsRepository;

	@Override
	@Transactional
	public SaleOrder save(SaleOrdersDTO dto) {
		Integer idCliente = dto.getClient_Id();
		Client client = clientsRepository.findById(idCliente)
				.orElseThrow(() -> new BusinessRoleException("Client not exist !!"));

		SaleOrder order = new SaleOrder();
		order.setTotal(dto.getTotal());
		order.setOrderDate(LocalDate.now());
		order.setClient(client);
		order.setStatus(OrderStatus.REALIZADO);

		List<OrderItem> orderItems = transformItems(order, dto.getItems());
		repository.save(order);
		itemsRepository.saveAll(orderItems);
		order.setItems(orderItems);

		return order;
	}

	private List<OrderItem> transformItems(SaleOrder order, List<OrderItemDTO> items) {

		if (items.isEmpty()) {
			throw new BusinessRoleException("Order has no items !!!");
		}

		return items.stream().map(dto -> {
			Integer product_Id = dto.getProduct_Id();
			Product product = productsRepository.findById(product_Id)
					.orElseThrow(() -> new BusinessRoleException("Product no has exist : " + product_Id));

			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(dto.getQuantity());
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			return orderItem;
		}).collect(Collectors.toList());

	}

	@Override
	public Optional<SaleOrder> getOrderInfo(Integer id) {
		return repository.findByIdFetchItems(id);
	}

	@Override
	@Transactional
	public void statusUpdate(Integer id, OrderStatus statusPedido) {
		repository.findById(id).map(order -> {
			order.setStatus(statusPedido);
			return repository.save(order);
		}).orElseThrow(() -> new OrderNotFoundException());

	}

}
