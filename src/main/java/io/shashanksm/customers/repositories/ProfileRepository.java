package io.shashanksm.customers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.shashanksm.customers.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
	@Query("SELECT p FROM Profile p WHERE p.account.id = :accountId")
	public Optional<Profile> findByAccountId(Long accountId);
	
}
