package com.surveybuilder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.surveybuilder.dao.RespondentDao;
import com.surveybuilder.dto.Answer;
import com.surveybuilder.entity.Respondent;
import com.surveybuilder.exception.ResourceNotFoundException;

@Service
public class RespondentServiceImpl implements RespondentService{

	public static final Logger logger = LoggerFactory.getLogger(RespondentServiceImpl.class);

	
	@Autowired
	RespondentDao rr;
	
	/****************************************************************************************************************************
	 - Method Name      : createRespondentService
	 - Input Parameters :Respondent s
	 - Return type      : String
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : Create the Respondent information entered by Respondent and store into  the database.
	  ****************************************************************************************************************************/ 

	//createRespondentService
	@Override
	public Respondent createRespondentService(Respondent s) {
		logger.info("createRespondentService");
		Respondent a = rr.save(s);
		if(a == null) {
			 new ResourceNotFoundException("Can not create Respondent :: ");
		}
		
		return a ;
	}
	
	/****************************************************************************************************************************
	 - Method Name      : viewRespondentByIdService
	 - Input Parameters : long id
	 - Return type      : Respondent
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : view the Respondent information entered by Respondent from the database.
	  ****************************************************************************************************************************/ 
	

	//viewRespondentByIdService
	@Override
	public Respondent viewRespondentByIdService(long id) {
		logger.info("viewRespondentByIdService");
	Respondent a = rr.findRespondentById(id);
		
		if(a == null) {
			 new ResourceNotFoundException("Respondent not found for this id :: "+ id);
		}
		return a;
	}
	
	
	/****************************************************************************************************************************
	 - Method Name      : updateRespondentService
	 - Input Parameters : Respondent s, long id
	 - Return type      : Respondent
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : update the Respondent information entered by Respondent and store into  the database.
	  ****************************************************************************************************************************/ 

	//updateRespondentService
	@Override
	public Respondent updateRespondentService(Respondent s, long id) throws ResourceNotFoundException {

		logger.info("updateRespondentService");
		Respondent a = rr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Respondent not found for this id :: " + id));
		
		s.setRespondentId(a.getRespondentId());
		
		final Respondent updatedA= rr.save(s);
		return updatedA;
	}

	
	
	/****************************************************************************************************************************
	 - Method Name      : deleteRespondentByIdService
	 - Input Parameters :long id
	 - Return type      : Boolean
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : Delete the Respondent information entered by Respondent and from  the database.
	  ****************************************************************************************************************************/ 
	
	//deleteRespondentByIdService
	@Override
	public boolean deleteRespondentByIdService(long id) throws ResourceNotFoundException {
	
		logger.info("deleteRespondentByIdService");
		
		Respondent a = rr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Respondent not found for this id :: " + id));
		
		rr.deleteById(id);
		
		if(a == null)
			return false;
		else
			return true;
	}
	
	
	/****************************************************************************************************************************
	 - Method Name      : listAllRespondentService
	 - Input Parameters :
	 - Return type      : List<Respondent>
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : view all the Respondent information from  the database.
	  ****************************************************************************************************************************/ 
	

	//listAllRespondentService
	@Override
	public List<Respondent> listAllRespondentService() {
		logger.info("get listof all respondent service");
		return rr.findAll();
	}
	

	/****************************************************************************************************************************
	 - Method Name      : authRespondent
	 - Input Parameters : String emailId, String pass
	 - Return type      : Respondent
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : authenticate the Respondent information entered by Respondent and from  the database.
	  ****************************************************************************************************************************/ 
	

	//auth respondent
	@Override
	public Respondent authRespondent(String emailId, String pass) {
		logger.info("authentication of respondent");
		return rr.authRespondent(emailId, pass);
	}
	
	@Autowired
	RestTemplate rest;
	
	/****************************************************************************************************************************
	 - Method Name      : createAnswerService
	 - Input Parameters : AnswerDto ans
	 - Return type      : String
	 - Author           : Capgemini
	 - Creation Date    : 23-04-2021
	 - Description      : create the question entered by respondent and store into  the database.
	  ****************************************************************************************************************************/ 
	
	@Override
	public String createAnswerService(Answer ans) {

		String url = "http://localhost/survey/answer/createAnswer";

			String a = rest.postForObject(url, ans, String.class);
			//rest.postForObject(url, object, class)
			
			return a;
			
		}
	
}
