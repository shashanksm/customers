package io.shashanksm.customers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.shashanksm.customers.entities.Preference;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long>{

}
