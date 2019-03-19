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
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class IpPrefix {
	 


	//@Temporal(TemporalType.DATE)
	//@Temporal(DATE)
	@DateTimeFormat (pattern="yyyy-MMM-dd' 'HH:mm:ss")
	private LocalDateTime creationDate;
	@DateTimeFormat (pattern="yyyy-MMM-dd' 'HH:mm:ss")
	private LocalDateTime lastUpdate;
	private String AS;
	private String VRF;
	private String version; 
	//@NonNull 
	private boolean nested;
	@NonNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String description;
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		System.out.println("#####Wywołanie setCreationDate("+creationDate+")");
		this.creationDate = creationDate;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		System.out.println("#####Wywołanie setLastUpdate("+lastUpdate+")");
		this.lastUpdate = lastUpdate;
	}
	public String getAS() {
		return AS;
	}
	public void setAS(String aS) {
		System.out.println("^^^^^^^^^^^^^wywołanie setAS("+aS+")");
		AS = aS;
	}
	public String getVRF() {
		return VRF;
	}
	public void setVRF(String vRF) {
		System.out.println("^^^^^^^^^^^^^^wywołanie setVRF("+vRF+")");
		VRF = vRF;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isNested() {
		return nested;
	}
	public void setNested(boolean nested) {
		this.nested = nested;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		System.out.println("Wywołanie setDescription("+description+")");
		this.description = description;
		System.out.println("Description po setDescription = "+ this.getDescription());
	} 
	
	//@NonNull
	//@OneToMany
	//private User user; 
	 
}
