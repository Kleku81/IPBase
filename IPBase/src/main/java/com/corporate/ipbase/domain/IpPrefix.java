package com.corporate.ipbase.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class IpPrefix {
	 
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	//@NonNull
	//@Temporal(TemporalType.DATE)
	private LocalDateTime creationDate;
	private LocalDateTime lastUpdate;
	private String AS;
	private String VRF;
	private String version; 
	//@NonNull 
	private boolean nested;
	@NonNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String description; 
	
	//@NonNull
	//@OneToMany
	//private User user; 
	 
}
