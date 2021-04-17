package com.cg.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.Respondent;

@Repository
public interface RespondentDAO extends JpaRepository<Respondent, Long> {

	@Query("select r from Respondent r where r.respondentId = ?1")
	public List<Respondent> findRespondentById(Long id);
	
	@Query("select a from Respondent a where (a.emailId = ?1 and a.password = ?2)")
	public List<Respondent> authRespondent(String emailId, String pass);
}
