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

import com.surveybuilder.entity.*;
import com.surveybuilder.exception.ResourceNotFoundException;
import com.surveybuilder.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	public static final Logger logger = LoggerFactory.getLogger(AdminController.class);	

	@Autowired
	private AdminService as ;
	
	@Autowired
	RestTemplate rest;	
	

	/****************************************************************************************************************************
	 - Method Name      : authAdminController
	 - Input Parameters : String emailId, String pass
	 - Return type      :Admin
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : authenticate the Admin information entered by Admin and from  the database.
	  ****************************************************************************************************************************/ 
	

	
	//authentication of admin
	@GetMapping("authAdmin/{id}/{pass}")
	public String authAdminController(@PathVariable("id") long id, @PathVariable("pass") String pass){
		logger.info("admin authentication controller");
		if( as.authAdmin(id, pass) != null)
			return "Login Successful";
		else
			return "Login failed";
	}
	
	/****************************************************************************************************************************
	 - Method Name      : createAdminController
	 - Input Parameters : Admin s
	 - Return type      : String
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : Create the Admin information entered by Admin and store into  the database.
	  ****************************************************************************************************************************/ 


	//create admin
	@PostMapping("createAdmin")
	public Admin createAdminController(@Valid @RequestBody Admin s) {
		logger.info("admin controller createadmin");
		return as.createAdminService(s);
	}
	
	/****************************************************************************************************************************
	 - Method Name      : viewAdminByIdController
	 - Input Parameters : long id
	 - Return type      : Admin
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : view the Admin information entered by Admin from the database.
	  ****************************************************************************************************************************/ 
	
	
	//view admin data by id
	@GetMapping("viewAdminById/{id}")
	public Admin viewAdminByIdController(@PathVariable("id") long id){
		logger.info("admin controller viewbyid");
		Admin a = as.viewAdminByIdService(id);
		return a;
	}
	
	/****************************************************************************************************************************
	 - Method Name      : updateAdminController
	 - Input Parameters : Admin s, long id
	 - Return type      : Admin
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : update the Admin information entered by Admin and store into  the database.
	  ****************************************************************************************************************************/ 

	
	//update admin data by id
	@PutMapping("updateAdmin/{id}")
	public Admin updateAdminController(@RequestBody Admin s, @PathVariable("id") long id) throws ResourceNotFoundException {
		logger.info("updateAdmin admin controller");
		return as.updateAdminService(s, id);
	}
	
	
	/****************************************************************************************************************************
	 - Method Name      : deleteAdminByIdController
	 - Input Parameters : long id
	 - Return type      : Boolean
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : Delete the Admin information entered by Admin and from  the database.
	  ****************************************************************************************************************************/ 
	
	
	//delete by id
	@DeleteMapping("deleteAdminById/{id}")
	public String deleteAdminByIdController(@PathVariable("id") long id) throws ResourceNotFoundException{
		logger.info("adminController delete by id");
		if(as.deleteAdminByIdService(id))
			return "Record deleted Successfully";
		else
			return "Can not delete record";
	}
	

	/****************************************************************************************************************************
	 - Method Name      : listAllAdminController
	 - Input Parameters :
	 - Return type      : List<Admin>
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : view all the Admin information from  the database.
	  ****************************************************************************************************************************/ 
	

	
	//get all admin
	@GetMapping("listAllAdmin")
	public List<Admin> listAllAdminController(){
		logger.info("listAllAdmin AdminController");
		return as.listAllAdminService();
	}
	
	//get all survey using resttemplate
			@GetMapping(value = "/viewallsurveys")
			public ResponseEntity<String> viewAllSurvey() {
				logger.info("get all surveys from admin controller");
				String survey = rest.getForObject("http://localhost/survey/survey/AllSurvey", String.class);
				return ResponseEntity.ok(survey);
			}

			//get all surveyor using resttemplate
					@GetMapping(value = "/viewallsurveyors")
					public ResponseEntity<String> viewAllSurveyor() {
						logger.info("get all surveys from admin controller");
						String surveyor = rest.getForObject("http://localhost/surveyor/surveyor/AllSurveyor", String.class);
						return ResponseEntity.ok(surveyor);
					}

					//get all survey using resttemplate
					@GetMapping(value = "/viewallrespondent")
					public ResponseEntity<String> viewAllRespondent() {
						logger.info("get all Respondent from admin controller");
						String Respondent = rest.getForObject("http://localhost/respondent/respondentms/listAllRespondent", String.class);
						return ResponseEntity.ok(Respondent);
					}

		
	}


