package com.corporate.ipbase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import data.IpPrefixRepository;
import domain.IpPrefix;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;

@Configuration
public class DevelopmentConfig {
	
	  @Bean
	  public CommandLineRunner dataLoader(IpPrefixRepository repo) {
		  
		  return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				//IPAddress address = new IPAddressString("1.2.3.4").getAddress();
				String bytes = "1,2,3";
				IpPrefix prefix =  new IpPrefix(bytes);
				repo.save(prefix);
				
			}
		};
	  }

}
