package com.foo.bar.spring.jpa.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foo.bar.spring.jpa.demo.entities.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
	
	public List<AuthorEntity> findByFirstName(String firstName);
	 
	@Query("SELECT a FROM AuthorEntity a WHERE a.lastName = :lastName")
	public List<AuthorEntity> findByLastName(@Param("lastName") String lastName);

}