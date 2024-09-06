package io.shashanksm.customers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.shashanksm.customers.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	@Query("SELECT a FROM Address a WHERE a.account.id = :accountId")
	public List<Address> findByAccountId(Long accountId);
}
