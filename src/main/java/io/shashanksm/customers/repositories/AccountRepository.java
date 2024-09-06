package io.shashanksm.customers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.shashanksm.customers.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	
}
