package com.corporate.ipbase.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


//@Data
//@RequiredArgsConstructor
//@NoArgsConstructor
@Entity
public class User {
	
	public User() {
		super();
	}
	public User(@NonNull String firstName, @NonNull String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id = -1L;
	
	@NonNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String firstName;
	@NonNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String lastName;
	public String getFirstName() {
		
		return firstName;
	}
	public void setFirstName(String firstName) {
		System.out.println("Ustawiam First Name");
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		System.out.println("Ustawiam Last Name");
		this.lastName = lastName;
	}
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
