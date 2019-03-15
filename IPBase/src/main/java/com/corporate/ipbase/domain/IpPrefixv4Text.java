package com.corporate.ipbase.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.corporate.ipbase.validator.SuperPrefixConstraint;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


public class IpPrefixv4Text extends IpPrefix{
	


	
	private String id;
	@NonNull
	@Pattern(regexp = "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\/(3[0-2]|[1-2][0-9]|[1-9])",message="Bad pattern")
	private String prefix;
	
	
	//==========
	public IpPrefixv4Text(
			 				String id,
							LocalDateTime lastUpdate,
							LocalDateTime creationDate,
							String prefix,
							String AS,
							String VRF,
							boolean nested,  
							String version,
							@Size(min = 5, message = "Name must be at least 5 characters long") String description) {
	this.setId(id);
	super.setLastUpdate(lastUpdate);
	super.setCreationDate(creationDate);
	super.setAS(AS);
	super.setVRF(VRF);
	super.setNested(nested);
	super.setVersion(version);
	this.prefix = prefix;
	super.setDescription(description);
	}
	
	public IpPrefixv4Text() {
		System.out.println("Wywołanie konstruktora default IpPrefixv4Text()"); 
		super.setLastUpdate(null);
		super.setCreationDate(null);
		super.setAS("");
		super.setVRF("");
		super.setNested(false);
		super.setVersion("");
		this.prefix ="";
		super.setDescription("");
	}
   
	
	
	public IpPrefixv4 converter() {
		

		String[] prefix_table = new String[] {};
	    prefix_table = this.getPrefix().split("\\.|\\/");
		byte[] byte_table = {Byte.valueOf(prefix_table[0]),
							 Byte.valueOf(prefix_table[1]),
							 Byte.valueOf(prefix_table[2]),
							 Byte.valueOf(prefix_table[3])};
	   /* for(byte b : byte_table) {
	    System.out.println("drukowanie");
	    System.out.println(b);
	    System.out.println("koniec drukowania drukowanie");
	    }*/
		//return new IpPrefixv4(LocalDateTime.now(), byte_table, Integer.valueOf(prefix_table[4]),4,this.getDescription());
	    if(this.getCreationDate()== null)
	    {
	    	this.setCreationDate(LocalDateTime.now()); 
	    }
	    
	    if(this.getLastUpdate()== null)
	    {
	    	this.setLastUpdate(LocalDateTime.now()); 
	    }
	    
		
		return new IpPrefixv4(
						      this.getId(),
							  this.getLastUpdate(),
				  			  this.getCreationDate(),
				              this.getAS(),
				              this.getVRF(),
				              this.isNested(), 
				              byte_table, 
				              Integer.valueOf(prefix_table[4]), 
				              this.getVersion(),
	           	              this.getDescription()); 
		
	}

	@Override
	public String toString() {
		return "IpPrefixv4Text [prefix=" + prefix + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		System.out.println("Wywołanie setId("+id+")");
		this.id = id;
		System.out.println("Id po set ID " + this.id);
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	} 
	
	public boolean compare(IpPrefixv4Text to_compare_prefix) {
		
		System.out.println("Value this.getAS() == to_compare_prefix.getAS() = " + this.getAS().equals(to_compare_prefix.getAS()));
		
		if (this.getAS().equals(to_compare_prefix.getAS()) &&
			this.getDescription().equals(to_compare_prefix.getDescription()) &&
			this.getPrefix().equals(to_compare_prefix.getPrefix()) &&
			this.getVRF().equals(to_compare_prefix.getVRF()))
		{	
			return true;
		}
		
		return false;
	}
	public String testPut() {
		
		return "testPut";
	}
	
	

}
