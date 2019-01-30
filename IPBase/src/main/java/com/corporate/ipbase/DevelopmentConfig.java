package com.corporate.ipbase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corporate.ipbase.data.IpPrefixRepository;
import com.corporate.ipbase.domain.IpPrefix;

import data.InventoryRepository;
import domain.MerchandiseEntity;
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
				byte[] bytes = {1,2,3};
				String[] test_addr = {"1.1.1.1","2.2.2.2", };
				for (String s : test_addr)
					System.out.println(s + " " + IpPrefix.isValidIPV4(s));
				IpPrefix prefix =  new IpPrefix(bytes,32,4);
				repo.save(prefix);
			
				
				/*MerchandiseEntity pants = new MerchandiseEntity(
						  new Double(34.99), "Pair of Pants");
				repo.save(pants);*/
			}
		};
	  }

}
