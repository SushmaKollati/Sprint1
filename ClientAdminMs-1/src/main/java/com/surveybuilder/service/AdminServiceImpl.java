package com.surveybuilder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.surveybuilder.dao.adminDao;
import com.surveybuilder.entity.Admin;
import com.surveybuilder.exception.ResourceNotFoundException;

@Service
public class AdminServiceImpl implements AdminService{

	public static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	
	@Autowired
	adminDao sr;
	
	/****************************************************************************************************************************
	 - Method Name      : createAdminService
	 - Input Parameters : Admin s
	 - Return type      : String
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : Create the Admin information entered by Admin and store into  the database.
	  ****************************************************************************************************************************/ 

	
	//create admin service
	@Override
	public Admin createAdminService(Admin s) {
		Admin a = sr.save(s);
		if(a == null) {
			 new ResourceNotFoundException("Can not create admin profile :: ");
		}
		logger.info("create admin service");
		return a;
	}
	
	/****************************************************************************************************************************
	 - Method Name      : viewAdminByIdService
	 - Input Parameters : long id
	 - Return type      : Admin
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : view the Admin information entered by Admin from the database.
	  ****************************************************************************************************************************/ 
	

	//view admin by id service
	@Override
	public Admin viewAdminByIdService(long id) {
		logger.info("view admin by id service");
	Admin a = sr.findAdminById(id);
		
		if(a == null) {
			 new ResourceNotFoundException("Admn not found for this id :: "+ id);
		}
		return a;
	}

	
	
	/****************************************************************************************************************************
	 - Method Name      : updateAdminService
	 - Input Parameters : Admin s, long id
	 - Return type      : Admin
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : update the Admin information entered by Admin and store into  the database.
	  ****************************************************************************************************************************/ 

	//update admin service
	@Override
	public Admin updateAdminService(Admin s, long id) throws ResourceNotFoundException {
		logger.info("update admin service");
		Admin a = sr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id :: " + id));
		//Admin a = sr.findAdminById(id);
		
		s.setAdminId(a.getAdminId());
		
		final Admin updatedA= sr.save(s);
		return updatedA;
	}

	/****************************************************************************************************************************
	 - Method Name      : deleteAdminByIdService
	 - Input Parameters : long id
	 - Return type      : Boolean
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : Delete the Admin information entered by Admin and from  the database.
	  ****************************************************************************************************************************/ 
	
	//delete admin by id service
	@Override
	public boolean deleteAdminByIdService(long id) throws ResourceNotFoundException {
		logger.info("deleteAdminByIdService");
		Admin a = sr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id :: " + id));
		
		//Admin a = sr.findAdminById(id);
		
		sr.deleteById(id);
		
		if(a == null)
			return false;
		else
			return true;	
	}
	

	/****************************************************************************************************************************
	 - Method Name      : listAllAdminService
	 - Input Parameters :
	 - Return type      : List<Admin>
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : view all the Admin information from  the database.
	  ****************************************************************************************************************************/ 
	


	//list all admin service
	@Override
	public List<Admin> listAllAdminService() {
		logger.info("list all admin service");
		return sr.findAll();
	}

	
	/****************************************************************************************************************************
	 - Method Name      : authAdmin
	 - Input Parameters : String emailId, String pass
	 - Return type      :Admin
	 - Author           : Capgemini
	 - Creation Date    : 19-04-2021
	 - Description      : authenticate the Admin information entered by Admin and from  the database.
	  ****************************************************************************************************************************/ 
	
	
	//authentication service
	@Override
	public Admin authAdmin(long id, String pass) {
		logger.info("authAdmin service");
		return sr.authAdmin(id, pass);
	}

}
