package io.pivotal.training.repository;

import io.pivotal.training.model.Nut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutsRepository extends JpaRepository<Nut, Long>{
}
