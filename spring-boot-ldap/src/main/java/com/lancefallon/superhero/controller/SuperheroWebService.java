package com.lancefallon.superhero.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lancefallon.superhero.model.Superhero;
import com.lancefallon.superhero.service.PersonService;
import com.lancefallon.superhero.service.SuperheroService;

@RestController
@RequestMapping(value="/api/superhero")
public class SuperheroWebService {

	@Autowired
	private SuperheroService superheroService;
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/secure", method=RequestMethod.GET)
	public ResponseEntity<List<Superhero>> getSuperheroes(Authentication user){
		return new ResponseEntity<>(superheroService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="secure/persons", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getInfo(){
		return new ResponseEntity<>(personService.getAllPersonNames(), HttpStatus.OK);
	}
	
	@RequestMapping(value="secure/account", method=RequestMethod.GET)
	public ResponseEntity<Map<String,String>> getInfo(Authentication auth){
		return new ResponseEntity<>(personService.getPersonByUsername(auth), HttpStatus.OK);
	}
	
	@RequestMapping(value="secure/support", method=RequestMethod.GET)
	public ResponseEntity<List<Map<String,String>>> getSupportTypes(){
		return new ResponseEntity<>(personService.getSupportTypes(), HttpStatus.OK);
	}
	
}
