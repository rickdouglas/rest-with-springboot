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

import br.com.erudio.data.vo.BookVO;
import br.com.erudio.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService services;
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"} )
	public BookVO findById(@PathVariable("id") Long id){
		BookVO bookVO = services.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}
	
	@GetMapping(produces = {"application/json", "application/xml"})
	public List<BookVO> findAll(){
		List<BookVO> books = services.findAll();
		books.stream().forEach(p -> 
		p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel())
		);
		return books;
	}
	
	@PostMapping(produces = {"application/json", "application/xml"},
				 consumes = {"application/json", "application/xml"})
	public BookVO create(@RequestBody BookVO book){
		BookVO bookVO = services.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
		
	}
	
	@PutMapping(produces = {"application/json", "application/xml"},
			 	consumes = {"application/json", "application/xml"})
	public BookVO update(@RequestBody BookVO book){
		BookVO bookVO = services.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		services.delete(id);
		return ResponseEntity.ok().build();
	}
}
