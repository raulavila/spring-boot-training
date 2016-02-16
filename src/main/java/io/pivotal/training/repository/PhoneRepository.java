package io.pivotal.training.repository;

import io.pivotal.training.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long>{
}
