package com.foo.bar.spring.jpa.demo.tests.repositories;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foo.bar.spring.jpa.demo.config.PersistenceContextConfig;
import com.foo.bar.spring.jpa.demo.entities.AuthorEntity;
import com.foo.bar.spring.jpa.demo.repositories.AuthorRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceContextConfig.class)
public class AuthorRepositoryTest {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthorRepositoryTest.class);

	@Autowired
	private AuthorRepository authorRepository;

	@Test
	public void testCreateAuthorEntity() {
		AuthorEntity author = new AuthorEntity();
		author.setFirstName("Foo");
		author.setLastName("Bar");

		AuthorEntity saved = authorRepository.save(author);

		Assert.assertNotNull(saved);
		Assert.assertNotNull(saved.getId());
		Assert.assertNotNull(saved.getDateCreate());
	}

	@Test
	public void testFindAuthorEntity() {
		Collection<AuthorEntity> authors = authorRepository.findAll();

		Assert.assertNotNull(authors);

		Assert.assertNotEquals(authors.size(), 0);
		log(authors.toArray(new AuthorEntity[0]));
	}
	
	@Test
	public void testFindAuthorEntityByFirstName() {
		Collection<AuthorEntity> authors = authorRepository.findByFirstName("Author A");

		Assert.assertNotNull(authors);

		Assert.assertNotEquals(authors.size(), 0);
		log(authors.toArray(new AuthorEntity[0]));
	}
	
	@Test
	public void testFindAuthorEntityByLastName() {
		Collection<AuthorEntity> authors = authorRepository.findByLastName("Author B");

		Assert.assertNotNull(authors);

		Assert.assertNotEquals(authors.size(), 0);
		log(authors.toArray(new AuthorEntity[0]));
	}

	@Test
	public void testUpdateAuthorEntity() {
		Collection<AuthorEntity> authors = authorRepository.findAll();
		Assert.assertNotNull(authors);

		AuthorEntity toUpdate = authors.iterator().next();
		Assert.assertNotNull(toUpdate);

		toUpdate.setLastName("Updated");
		
		authorRepository.save(toUpdate);

		AuthorEntity updated = authorRepository.findOne(toUpdate.getId());
		Assert.assertNotNull(updated);
		Assert.assertEquals(updated.getLastName(), toUpdate.getLastName());
		Assert.assertNotNull(updated.getDateModify());
		
		log(toUpdate);
		log(updated);
	}

	private void log(AuthorEntity... authors) {
		for (AuthorEntity author : authors) {
			logger.info("found person :{}", author);
		}
	}

}
