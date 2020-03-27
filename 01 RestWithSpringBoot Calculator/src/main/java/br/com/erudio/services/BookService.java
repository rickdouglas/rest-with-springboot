package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository repository;
	
	public BookVO create (BookVO Book) {
		var entity = DozerConverter.parseObject(Book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	
	public BookVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id")) ;
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public BookVO update(BookVO Book) {
		var entity = repository.findById(Book.getKey()).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id")) ;;
		
		entity.setAuthor(Book.getAuthor());
		entity.setLauchDate(Book.getLauchDate());
		entity.setPrice(Book.getPrice());
		entity.setTitle(Book.getTitle());
		
		var vo =  DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public List<BookVO> findAll(){
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}
	
	public void delete(Long id) {
		Book entity = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id")) ;;
		repository.delete(entity);
	}


}
