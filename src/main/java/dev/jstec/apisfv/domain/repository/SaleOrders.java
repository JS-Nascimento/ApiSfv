package dev.jstec.apisfv.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.jstec.apisfv.domain.entity.Client;
import dev.jstec.apisfv.domain.entity.SaleOrder;

public interface SaleOrders extends JpaRepository<SaleOrder, Integer>{
	
	List<SaleOrder> findByClient(Client client);

	@Query(" select p from SaleOrder p left join fetch p.items where p.id = :id ")
	Optional<SaleOrder> findByIdFetchItems(@Param("id") Integer id);
}
	