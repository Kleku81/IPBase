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
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class IpPrefix {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id = -1L;
	
	//@NonNull
	//@Temporal(TemporalType.DATE)
	private LocalDateTime lastUpdate;
	//@NonNull 
	private boolean nested;
	
	//@NonNull
	//@OneToMany
	//private User user; 
	 
}
