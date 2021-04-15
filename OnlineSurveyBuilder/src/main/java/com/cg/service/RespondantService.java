package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cg.domain.Respondant;
import com.cg.repo.RespondantRepo;
@Component
@Service
public class RespondantService implements RespondantServiceInterface {

	@Autowired
	private RespondantRepo repo;
	@Override
	public Respondant createrespo(Respondant respo) {
		return repo.save(respo);
	}
	

}