package br.com.erudio.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.util.List;

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

import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService services;
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public PersonVO findById(@PathVariable("id") Long id){
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@GetMapping(produces = {"application/json", "application/xml"})
	public List<PersonVO> findAll(){
		return services.findAll();
	}
	
	@PostMapping(produces = {"application/json", "application/xml"},
				 consumes = {"application/json", "application/xml"})
	public PersonVO create(@RequestBody PersonVO person){
		return services.create(person);
	}
	
	@PutMapping(produces = {"application/json", "application/xml"},
			 	consumes = {"application/json", "application/xml"})
	public PersonVO update(@RequestBody PersonVO person){
		return services.update(person);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}
