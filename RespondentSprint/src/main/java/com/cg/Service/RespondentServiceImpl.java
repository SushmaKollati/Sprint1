package com.cg.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.DAO.RespondentDAO;
import com.cg.Exception.ResourceNotFoundException;
import com.cg.entity.Respondent;

@Service
public class RespondentServiceImpl implements RespondentService {
	
	@Autowired
	RespondentDAO rr;
	
	

	@Override
	public Respondent createRespondentService(Respondent s) {
		Respondent a = rr.save(s);
		if(a == null) {
			new ResourceNotFoundException("Can Not Create Respondent :: ");
		}
		
		// TODO Auto-generated method stub
		return a;
	}

	@Override
	public Respondent viewRespondentByIdService(long id) {
		Respondent a = (Respondent) rr.findRespondentById(id);
		 if(a==null) {
			 new ResourceNotFoundException("Respondent not Found for this id :: " + id);
		 }
		// TODO Auto-generated method stub
		return a;
	}

	@Override
	public Respondent updateRespondentService(Respondent s, long id) throws ResourceNotFoundException {
		Respondent a = rr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Respondent not found for this id ::" + id));
		s.setRespondentId(a.getRespondentId());
		final Respondent updatedA = rr.save(s);
		
		return updatedA;
	}

	@Override
	public boolean deleteResponendtByIdService(long id) throws ResourceNotFoundException {
		Respondent a = rr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Respondent not found for this id ::" + id));
        rr.deleteById(id);
        if(a == null)
        	
		return false;
        else 
        	return true;
	}

	@Override
	public List<Respondent> listAllRespondentService() {
		// TODO Auto-generated method stub
		return rr.findAll();
		
	}

	@Override
	public Respondent autoRespondent(String emailId, String pass) {
		// TODO Auto-generated method stub
		return (Respondent) rr.authRespondent(emailId, pass);
	}

}
