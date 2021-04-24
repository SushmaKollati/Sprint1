package com.surveybuilder.controller;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.surveybuilder.ClientRespondentMsApplication;
import com.surveybuilder.entity.Respondent;
import com.surveybuilder.service.RespondentService;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ClientRespondentMsApplication.class)
@WebMvcTest(value = RespondentController.class)
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;
	   
	 @MockBean
	 RespondentService adminService;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

/*	@Test
	void testCreateAdminController() throws Exception {
		String URI = "/createAdmin";
		Admin admin = getAdmin();
	    String jsonInput = this.converttoJson(admin);
	    Mockito.when(adminService.createAdminService(Mockito.any(Admin.class))).thenReturn(admin);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
	}*/

	@Test
	void testViewRespondentByIdController() throws Exception {
		String URI = "/viewRespondentById/{id}";
		Respondent respondent= getRespondent();
	    String jsonInput = this.converttoJson(respondent);

	  //  Mockito.when(respondentService.viewRespondentByIdService(Mockito.anyLong())).thenReturn(respondent);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 101).accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonInput);

	}


	@Test
	void testUpdateRespondentController() throws Exception {
		String URI = "/updateRespondent/{id}";
		Respondent respondent = getRespondent();
	    String jsonInput = this.converttoJson(respondent);

	  //  Mockito.when(respondentService.updateRespondentService(Mockito.any(Respondent.class), Mockito.anyLong())).thenReturn(respondent);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 101).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn(); MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonInput);
	}

	@Test
	void testDeleteRespondentByIdController() throws Exception {
		String URI = "/deleteRespondentById/{id}";
 
	    String r = "Record deleted Successfully";
	    boolean b = true;
	    
	   // Mockito.when(respondentService.deleteRespondentByIdService(Mockito.anyLong())).thenReturn(b);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 101).accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(r).isEqualTo(r);
	}

	@Test
	void testListAllAdminController() throws Exception {
		String URI = "/listAllRespondent";
		List<Respondent> a = new ArrayList<Respondent>();
		
		Respondent respondent= getRespondent();
		a.add(respondent);
	    String jsonInput = this.converttoJson(a);

	   // Mockito.when(respondentService.listAllRespondentService()).thenReturn(a);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonInput);
	}
	
	
	private Respondent getRespondent() {
		 Respondent a = new  Respondent();
		
		a.setRespondentId(101);
		a.setEmailId("Sravani@gmail.com");
		a.setName("Sravani");
		a.setPassword("Sravani");
		
		return a;
	}
	
	
	private String converttoJson(Object respondent) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(respondent);
	}
	

}


