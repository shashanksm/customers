package io.shashanksm.customers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.shashanksm.customers.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT o FROM Order o WHERE o.account.id = :accountId")
	public List<Order> findByAccountId(Long accountId);
}
