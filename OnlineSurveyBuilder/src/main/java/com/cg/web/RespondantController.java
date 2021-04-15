package com.cg.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.domain.Respondant;
import com.cg.repo.RespondantRepo;
import com.cg.service.RespondantService;

@RestController
@Validated
@RequestMapping(value = "/respondant")
@CrossOrigin(origins="http://localhost:3000")
public class RespondantController {

	@Autowired
	private RespondantService service;

	@Autowired
	private RespondantRepo repo;

  @PostMapping("/create")
	public ResponseEntity<String> create(@Validated @RequestBody Respondant respo,BindingResult result) {
	  boolean y=repo.existsById(respo.getRespondantId());
          if(!y) {
		((CrudRepository<Respondant, Integer>) repo).save(respo);
		return new ResponseEntity<>("Respondant created successfully", HttpStatus.OK);
	}
          else {
        	  return new ResponseEntity<>("Respondant already exists", HttpStatus.BAD_REQUEST);
          } 
  }

@PutMapping("/update")
public ResponseEntity<String> update(@Validated @RequestBody Respondant respo,BindingResult result) {
	boolean y=repo.existsById(respo.getRespondantId());
	if(y) {
		repo.save(respo);
		return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
	}
	else{
		return new ResponseEntity<String>("NO Data Found", HttpStatus.BAD_REQUEST);
		}
	}
 @GetMapping("/view/{id}")
public Optional<Respondant>  viewById(@Validated @PathVariable Integer id){
	 	return repo.findById(id);
		}
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> deletebyid(@Validated @PathVariable Integer id){
	 boolean y=repo.existsById(id);
	 if(y) {
		 repo.deleteById(id);
		 return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
	 }
	 else {
		 return new ResponseEntity<String>("No data found",HttpStatus.BAD_REQUEST);	 }
 }
}