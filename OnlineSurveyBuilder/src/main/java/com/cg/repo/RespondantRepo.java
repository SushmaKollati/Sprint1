package com.cg.repo;

import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.CrudRepository;

import com.cg.domain.Respondant;

public interface RespondantRepo extends CrudRepository<Respondant,Integer>{

	Respondant save(Respondant respo);

}
