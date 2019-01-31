package com.corporate.ipbase.domain;

import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import inet.ipaddr.IPAddress;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Entity
public class IpPrefixv4 extends IpPrefix{

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private final Long id = -1L;
	  
	  @NonNull
	  private byte[] bytes;
	  @NonNull
	  private int mask; 
	  @NonNull
	  private int version;
	  
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



}
