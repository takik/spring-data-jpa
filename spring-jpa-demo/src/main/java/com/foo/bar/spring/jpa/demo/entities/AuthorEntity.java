package com.foo.bar.spring.jpa.demo.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@NamedQuery(name = "AuthorEntity.findByFirstName", query = "SELECT a FROM AuthorEntity a WHERE a.firstName = ?1")
@Table(name = "AUTHORS")
public class AuthorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "date_create", nullable = false)
	private Date dateCreate;
	
	@Column(name = "date_modify", nullable = true)
	private Date dateModify;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Version
	private long version = 0;
	
	@PreUpdate
    public void preUpdate() {
		dateModify =new Date(new java.util.Date().getTime());
    }
	
    @PrePersist
    public void prePersist() {
         dateCreate = new Date(new java.util.Date().getTime());
    }
	public Long getId() {
		return id;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public long getVersion() {
		return version;
	}

	public void update(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateModify() {
		return dateModify;
	}

	@Override
	public String toString() {
		final	char SEPERATOR='\t';
		StringBuilder builder = new StringBuilder();
		builder.append(this.firstName);
		builder.append(SEPERATOR);
		builder.append(this.lastName);
		builder.append(SEPERATOR);
		builder.append(this.dateCreate);
		builder.append(SEPERATOR);
		return builder.toString();
	}
}