package com.cg.Service;

import java.util.List;

import com.cg.Exception.ResourceNotFoundException;
import com.cg.entity.Respondent;

public interface RespondentService {
	
	public Respondent createRespondentService(Respondent s);
	public Respondent viewRespondentByIdService(long id);
	public Respondent updateRespondentService(Respondent s, long id) throws ResourceNotFoundException;
	public boolean deleteResponendtByIdService(long id) throws ResourceNotFoundException;
    public List<Respondent> listAllRespondentService();
    public Respondent autoRespondent(String emailId, String pass);
	
}
