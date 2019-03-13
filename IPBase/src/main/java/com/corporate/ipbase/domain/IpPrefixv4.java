package com.corporate.ipbase.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import inet.ipaddr.IPAddress;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter 
@Setter 
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Entity
@ToString 
public class IpPrefixv4 extends IpPrefix{


	public IpPrefixv4(LocalDateTime lastUpdate,
					  LocalDateTime creationDate,
					  String AS,
					  String VRF,
					  boolean nested, byte[] bytes, 
					  int mask, 
					  String version,
		           	  @Size(min = 5, message = "Name must be at least 5 characters long") String description) {
		super.setLastUpdate(lastUpdate);
		super.setCreationDate(creationDate);
		super.setAS(AS);
		super.setVRF(VRF);
		super.setNested(nested);
		super.setVersion(version);
		this.bytes = bytes;
		this.mask = mask;
		
		this.description = description;
	}

	public IpPrefixv4(LocalDateTime lastUpdate, byte[] bytes, int mask,  String version,		
			@Size(min = 5, message = "Name must be at least 5 characters long") String description) {
		super.setLastUpdate(lastUpdate);
		super.setVersion(version);
		this.bytes = bytes;
		this.mask = mask;
		this.description = description;
	}
	public IpPrefixv4(@NonNull LocalDateTime lastUpdate,
			@NonNull @Size(min = 5, message = "Name must be at least 5 characters long") String description) {
		super();
		this.description = description;
	}

	  //@NonNull
	  //@Size(min=4, max=4 )
	private byte[] bytes = new byte[4];
	  //@NonNull
	private int mask; 
	  //@NonNull
	//private int version;
	  //@NonNull
	  @Size(min=5, message="Name must be at least 5 characters long")
	private String description;
	  @OneToMany(
		        cascade = CascadeType.ALL,
		        orphanRemoval = true
		    )
	private List<IpPrefixv4> subNets = new ArrayList<>();
	  
	  //static private final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
	  //static private final String IPV4_REGEX = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	static private final String IPV4_REGEX = "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\/(3[0-2]|[1-2][0-9]|[1-9])";
	  //static private final String IPV4_REGEX = "(?:a{6})*";
	static private Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
	  
	static private final String IPV4_REGEX1 = "([a-z][0-9]){3,5}";
	static private Pattern IPV4_PATTERN1 = Pattern.compile(IPV4_REGEX1);
	  
	public static boolean isValidIPV4(final String s)
	   {          
	      return IPV4_PATTERN.matcher(s).matches();
	   }
	   
	public static boolean testValid(final String s)
	   {          
	      return IPV4_PATTERN1.matcher(s).matches();
	   }
	   
	public boolean isContained(IpPrefixv4 to_compare_prefix)
	   {
		   
		   if(this.getMask() <= to_compare_prefix.getMask() || to_compare_prefix.getMask() < 8)
		   { 
			   return false;
		   }
		   int compare_whole_bytes = to_compare_prefix.getMask()/8; 
		   
		   for (int i : IntStream.range(0, compare_whole_bytes-1).toArray())
		   {
			   if (this.getBytes()[i]!=to_compare_prefix.getBytes()[i])
			   {
				   return false;
			   }
		   }
		   
		   int compare_last_bits = to_compare_prefix.getMask()%8; 
		   
		   if (compare_last_bits == 0 || this.getBytes()[compare_whole_bytes]>>compare_last_bits == to_compare_prefix.getBytes()[compare_whole_bytes]>>compare_last_bits)
		   {
			   return true; 
		   }
		   else 
		   {	
			  return false;
		   }   
		  
	   }

	public String toStringPrefix() {
		
		return Byte.toString(this.getBytes()[0])+"."+Byte.toString(this.getBytes()[1])+"."+Byte.toString(this.getBytes()[2])+"."+Byte.toString(this.getBytes()[3])+"/"+Integer.toString(this.getMask());
		
	}
	
	
	public IpPrefixv4Text converter() {
		
	
		String string_prefix = this.toStringPrefix();

		
		return new IpPrefixv4Text(this.getLastUpdate(),
								  this.getCreationDate(), 
								  string_prefix, 
								  this.getAS(),
								  this.getVRF(),
								  this.isNested(),
								  this.getVersion(),
								  this.getDescription());
	} 
	
}
