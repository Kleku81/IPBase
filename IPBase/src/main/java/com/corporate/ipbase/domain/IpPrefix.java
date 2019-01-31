package com.corporate.ipbase.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class IpPrefix {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id = -1L;
	
	@NonNull
	private Date lastUpdate;
	@NonNull
	@OneToMany
	private User user; 
	 
}
