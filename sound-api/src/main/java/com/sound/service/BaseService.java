package com.sound.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


public class BaseService<T> {
	
	@Autowired
	private JpaRepository<T, Long> repository;
	
	private static final Sort sort = new Sort(Sort.Direction.DESC, "id");
	
	public T findById(Long id) {
		Optional<T> t  = repository.findById(id);
		if(t.isPresent()) {
			return t.get();
		}else {
			return null;
		}
	}
	
	public void deleteInBatch(Iterable<T> entities) {
		repository.deleteInBatch(entities);

	}
	
	
	
	public T save(T t) {
		
		return repository.save(t);
	}
	
	public Iterable<T> findAllById(Iterable<Long>ids){
		return repository.findAllById(ids);
	}
	
	public Iterable<T> findAll() {
		return repository.findAll(sort);
	}
	
	public List<T> findAll(Example<T> example,Sort s) {
		return repository.findAll(example,s);
	}
	
	public Iterable<T> findAll(Example<T> example ) {
		return repository.findAll(example, sort);
	}
	
	public List<T> findAll(T example ) {
		return repository.findAll(Example.of(example), sort);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	public Page<T> findAll(Example<T> example,Pageable pageable){
		return repository.findAll(example,pageable);
	}
	
	public  Page<T>  findAll(int page, int size){
		PageRequest pageable = PageRequest.of(page, size,sort);
		return repository.findAll(pageable);
	}
	
	public Page<T> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}

	public void deleteAll(Iterable<T> entities){
		 repository.deleteAll(entities);
	}
	public void delete(T entities){
		repository.delete(entities);
	}
}
