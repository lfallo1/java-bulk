package com.lancefallon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lancefallon.model.Character;
import com.lancefallon.model.Villain;
import com.lancefallon.repository.CharacterRepository;

@RestController
@RequestMapping("/api/character")
public class CharacterController {

	@Autowired
	private CharacterRepository characterRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Character>> list(){
		return new ResponseEntity<>(this.characterRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Villain> save(@RequestBody Villain villain){
		return new ResponseEntity<>(this.characterRepository.save(villain), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/findByAlias/{alias}", method=RequestMethod.GET)
	public ResponseEntity<Character> findByAlias(@PathVariable String alias){
		return new ResponseEntity<>(this.characterRepository.findByAlias(alias), HttpStatus.OK);
	}
	
}
