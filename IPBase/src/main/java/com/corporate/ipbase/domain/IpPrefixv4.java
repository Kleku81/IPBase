package com.corporate.ipbase.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;




//@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Entity
@ToString 
public class IpPrefixv4 extends IpPrefix{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	
	@NonNull
	@Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\/(3[0-2]|[1-2][0-9]|[1-9])",message="Bad pattern")
	//@Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\/(3[0-2]|[1-2][0-9]|[1-9])$",message="Bad pattern")
	private String prefix;
	@Size(min=4, max=4 )
	private byte[] bytes;
	  //@NonNull
	private int mask; 
	  //@NonNull 
	//private int version;
	  //@NonNull
	  @OneToMany(
		        cascade = CascadeType.ALL,
		        orphanRemoval = true
		    )
	private List<IpPrefixv4> subNets = new ArrayList<>();

	public IpPrefixv4() {
		super();
		this.setCreationDate(LocalDateTime.now());
		this.setLastUpdate(LocalDateTime.now());
	}

	public IpPrefixv4(
					  String id,
					  LocalDateTime lastUpdate,
					  LocalDateTime creationDate,
					  String AS,
					  String VRF,
					  boolean nested, 
					  //byte[] bytes, 
					  //int mask, 
					  String prefix,
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
		//this.stringToBytesMask(this.prefix);
		//this.bytes = bytes;
		//this.mask = mask;
		super.setDescription(description);
	}

	/*public IpPrefixv4(LocalDateTime lastUpdate, byte[] bytes, int mask,  String version,		
			@Size(min = 5, message = "Name must be at least 5 characters long") String description) {
		super.setLastUpdate(lastUpdate);
		super.setVersion(version);
		this.bytes = this.stringToBytes(this.prefix);
		this.mask = (int);
		this.description = description;
	}*/
	public IpPrefixv4(@NonNull LocalDateTime lastUpdate,
			@NonNull @Size(min = 5, message = "Name must be at least 5 characters long") String description) {
		super();
		super.setDescription(description);
	}

	
	  
	  //static private final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
	  //static private final String IPV4_REGEX = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
//	static private final String IPV4_REGEX = "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\/(3[0-2]|[1-2][0-9]|[1-9])";
	  //static private final String IPV4_REGEX = "(?:a{6})*";
//	static private Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
	  
//	static private final String IPV4_REGEX1 = "([a-z][0-9]){3,5}";
//	static private Pattern IPV4_PATTERN1 = Pattern.compile(IPV4_REGEX1);
	  
/*	public static boolean isValidIPV4(final String s)
	   {          
	      return IPV4_PATTERN.matcher(s).matches();
	   }
	   
	public static boolean testValid(final String s)
	   {          
	      return IPV4_PATTERN1.matcher(s).matches();
	   }*/

	   
	public boolean isContained(IpPrefixv4 to_compare_prefix)
	   {
		   
		   if(this.getMask() <= to_compare_prefix.getMask() || to_compare_prefix.getMask() < 8)
		   { 
			   return false;
		   }
		   int compare_whole_bytes = to_compare_prefix.getMask()/8; 
		   
		   for (int i : IntStream.range(0, compare_whole_bytes).toArray())
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

		
		return new IpPrefixv4Text(
								  this.getId(),
								  this.getLastUpdate(),
								  this.getCreationDate(), 
								  string_prefix, 
								  this.getAS(),
								  this.getVRF(),
								  this.isNested(),
								  this.getVersion(),
								  this.getDescription());
	} 
	
	static public byte[] stringToBytes(String prefix)
	{
		
	    String[] prefix_table = prefix.split("\\.|\\/");
		byte[] byte_table = {Byte.valueOf(prefix_table[0]),
							 Byte.valueOf(prefix_table[1]),
							 Byte.valueOf(prefix_table[2]),
							 Byte.valueOf(prefix_table[3])};
		
		return byte_table;
	}
	public void prefixToBytesMask()
	{
		
	    String[] prefix_table = this.prefix.split("\\.|\\/");
		if (prefix_table.length==5) {
		byte[] byte_table = {Byte.valueOf(prefix_table[0]),
							 Byte.valueOf(prefix_table[1]),
							 Byte.valueOf(prefix_table[2]),
							 Byte.valueOf(prefix_table[3])};
		
		this.bytes =  byte_table;
		this.mask = (int) Byte.valueOf(prefix_table[4]);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		
		this.prefix = prefix;
		//this.stringToBytesMask(this.prefix);
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public int getMask() {
		return mask;
	}

	public void setMask(int mask) {
		this.mask = mask;
	}

	public List<IpPrefixv4> getSubNets() {
		return subNets;
	}

	public void setSubNets(List<IpPrefixv4> subNets) {
		this.subNets = subNets;
	}
		

}
