package com.surveybuilder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.surveybuilder.dto.Answer;
import com.surveybuilder.entity.Respondent;
import com.surveybuilder.exception.ResourceNotFoundException;
import com.surveybuilder.service.RespondentService;

@RestController
@RequestMapping("/respondentms")
public class RespondentController {

	public static final Logger logger = LoggerFactory.getLogger(RespondentController.class);

	
	@Autowired
	RespondentService rs;
	
	@Autowired
	RestTemplate rest;	
	

	/****************************************************************************************************************************
	 - Method Name      : authRespondentController
	 - Input Parameters : String emailId, String pass
	 - Return type      : Respondent
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : authenticate the Respondent information entered by Respondent and from  the database.
	  ****************************************************************************************************************************/ 
	

	
//authentication of respondent
	@GetMapping("authRespondent/{emailId}/{pass}")
	public String authRespondentController(@PathVariable("emailId") String emailId, @PathVariable("pass") String pass){
		logger.info("authRespondent controller");
		if( rs.authRespondent(emailId, pass) != null)
			return "Login successful";
		else
			return "Login failed.. please check emailId and password";
	}
	

	/****************************************************************************************************************************
	 - Method Name      : createRespondentController
	 - Input Parameters : Respondent s
	 - Return type      : String
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : Create the Respondent information entered by Respondent and store into  the database.
	  ****************************************************************************************************************************/ 

	
	//create respondent controller
	@PostMapping("createRespondent")
	public Respondent createRespondentController(@Valid @RequestBody Respondent s) {
		logger.info("create respondent controller");
		return rs.createRespondentService(s);
	}
	

	/****************************************************************************************************************************
	 - Method Name      : viewRespondentByIdController
	 - Input Parameters : long id
	 - Return type      : Respondent
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : view the Respondent information entered by Respondent from the database.
	  ****************************************************************************************************************************/ 
	

	
	//viewRespondentById
	@GetMapping("viewRespondentById/{id}")
	public Respondent viewRespondentByIdController(@PathVariable("id") Long id){
		logger.info("viewRespondentById controller");
		Respondent r = rs.viewRespondentByIdService(id);
		return r;
	}
	
	
	/****************************************************************************************************************************
	 - Method Name      : updateRespondentController
	 - Input Parameters : Respondent s, long id
	 - Return type      : Respondent
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : update the Respondent information entered by Respondent and store into  the database.
	  ****************************************************************************************************************************/ 
	
	//update respondent by id
	@PutMapping("updateRespondent/{id}")
	public Respondent updateRespondentController(@RequestBody Respondent s, @PathVariable("id") long id) throws ResourceNotFoundException {
		logger.info("update respondent by id");
		return rs.updateRespondentService(s, id);
	}
	
	

	/****************************************************************************************************************************
	 - Method Name      : deleteRespondentByIdController
	 - Input Parameters : long id
	 - Return type      : Boolean
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : Delete the Respondent information entered by Respondent and from  the database.
	  ****************************************************************************************************************************/ 
	
	
	//delete respondent by id
	@DeleteMapping("deleteRespondentById/{id}")
	public String deleteRespondentByIdController(@PathVariable("id") Long id) throws ResourceNotFoundException{
		logger.info("delete respondent by id controller");
		if(rs.deleteRespondentByIdService(id))
			return "Record deleted Successfully";
		else 
			return "Can not delete record";
	}

	
	

	/****************************************************************************************************************************
	 - Method Name      : listAllRespondentController
	 - Input Parameters :
	 - Return type      : List<Respondent>
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : view all the Respondent information from  the database.
	  ****************************************************************************************************************************/ 
	

	//list all respondent
	@GetMapping("listAllRespondent")
	public List<Respondent> listAllRespondentController(){
		logger.info("get list of all respondents");
		return rs.listAllRespondentService();
	}
	
	
	

	//get all survey using resttemplate
		@GetMapping(value = "/viewallsurveys")
		public ResponseEntity<String> viewAllSurvey() {
			logger.info("get all surveys from admin controller");
			String survey = rest.getForObject("http://localhost/survey/survey/viewallsurveystatus", String.class);
			return ResponseEntity.ok(survey);
		}
		
		

		/****************************************************************************************************************************
		 - Method Name      : createAnswerController
		 - Input Parameters : AnswerDto ans
		 - Return type      : String
		 - Author           : Capgemini
		 - Creation Date    : 23-04-2021
		 - Description      : create the question entered by respondent and store into  the database.
		  ****************************************************************************************************************************/ 
		
		@PostMapping(value = "/createAnswer")
		public String addAnswer(@RequestBody Answer ans) {		
			return rs.createAnswerService(ans);

		}
	}


