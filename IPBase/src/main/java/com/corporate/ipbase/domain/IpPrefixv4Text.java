package com.corporate.ipbase.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.corporate.ipbase.validator.SuperPrefixConstraint;

import lombok.Data;
import lombok.NonNull;

@Data
public class IpPrefixv4Text extends IpPrefix{
	

	@NonNull
	@Size(min=5, message="Name must be at least 5 characters long")
	  private String description;
	
	@NonNull
	@Pattern(regexp = "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\/(3[0-2]|[1-2][0-9]|[1-9])",message="Bad pattern")
	
	  private String prefix;
	
	
	public IpPrefixv4 converter() {
		

		String[] prefix_table = new String[] {};
	    prefix_table = this.getPrefix().split("\\.|\\/");
		byte[] byte_table = {Byte.valueOf(prefix_table[0]),
							 Byte.valueOf(prefix_table[1]),
							 Byte.valueOf(prefix_table[2]),
							 Byte.valueOf(prefix_table[3])};
	    for(byte b : byte_table) {
	    System.out.println("drukowanie");
	    System.out.println(b);
	    System.out.println("koniec drukowania drukowanie");
	    }
		//return new IpPrefixv4(LocalDateTime.now(), byte_table, Integer.valueOf(prefix_table[4]),4,this.getDescription());
	    if(this.getCreationDate()== null)
	    {
	    	this.setCreationDate(LocalDateTime.now()); 
	    }
		
		return new IpPrefixv4(this.getLastUpdate(),
				  			  this.getCreationDate(),
				              this.getAS(),
				              this.getVRF(),
				              this.isNested(), 
				              byte_table, 
				              Integer.valueOf(prefix_table[4]), 
				              this.get
	           	              this.getDescription()); 
		
	} 

}
