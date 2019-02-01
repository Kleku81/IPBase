package com.corporate.ipbase;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corporate.ipbase.data.IpPrefixRepository;
import com.corporate.ipbase.domain.IpPrefixv4;

import data.InventoryRepository;
import domain.MerchandiseEntity;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import lombok.NonNull;

@Configuration
public class DevelopmentConfig {
	
	  @Bean
	  public CommandLineRunner dataLoader(IpPrefixRepository repo) {
		  
		  return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				//IPAddress address = new IPAddressString("1.2.3.4").getAddress();
				byte[] bytes = {1,2,3,4};
				
				//public IpPrefixv4(@NonNull Date lastUpdate, @NonNull byte[] bytes, @NonNull int mask, @NonNull int version) 
				String[] test_addr = {"aaaa","2.2.2.2/32","255.255.255.255" };
				for (String s : test_addr)
					System.out.println(s + " " + IpPrefixv4.isValidIPV4(s));
				IpPrefixv4 prefix =  new IpPrefixv4(LocalDateTime.now(),bytes,32,4);
				repo.save(prefix);
			
				
				/*MerchandiseEntity pants = new MerchandiseEntity(
						  new Double(34.99), "Pair of Pants");
				repo.save(pants);*/
			}
		};
	  }

}
