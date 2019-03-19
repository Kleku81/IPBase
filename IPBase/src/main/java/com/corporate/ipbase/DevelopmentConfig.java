package com.corporate.ipbase;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.corporate.ipbase.data.IpPrefixv4Repository;
import com.corporate.ipbase.domain.IpPrefixv4;

import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import lombok.NonNull;

@Configuration
public class DevelopmentConfig {
	
	
	/*@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {
	 
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	          .addResourceHandler("/webjars/**")
	          .addResourceLocations("/webjars/");
	    }
	}*/
	
	  @Bean
	  public CommandLineRunner dataLoader(IpPrefixv4Repository repo) {
		  
		  return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				//IPAddress address = new IPAddressString("1.2.3.4").getAddress();
				byte[] bytes = {1,1,0,0};
				byte[] bytes1 = {1,1,5,1};
				byte[] bytes2 = {1,1,6,0};
				byte[] bytes3 = {2,2,0,0};
				byte[] bytes4 = {2,2,5,0};
				byte[] bytes5 = {2,2,6,0};
				
				
				//public IpPrefixv4(@NonNull Date lastUpdate, @NonNull byte[] bytes, @NonNull int mask, @NonNull int version) 
				String[] test_addr = {"aaaa","2.2.2.2/32","255.255.255.255" };
				//for (String s : test_addr)
				
				IpPrefixv4 prefix1 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"2.1.1.1/24","4","prefix1");
				prefix1.prefixToBytesMask();
				//IpPrefixv4 prefix2 =  new IpPrefixv4(LocalDateTime.now(),bytes2,24,4,"prefix2");
				//IpPrefixv4 prefix3 =  new IpPrefixv4(LocalDateTime.now(),bytes3,16,4,"prefix3");
				//IpPrefixv4 prefix4 =  new IpPrefixv4(LocalDateTime.now(),bytes4,24,4,"prefix4");
				//IpPrefixv4 prefix5 =  new IpPrefixv4(LocalDateTime.now(),bytes5,24,4,"prefix5");
				/*prefix1.setNested(true);
				prefix.getSubNets().add(prefix1);
				prefix2.setNested(true);
				prefix.getSubNets().add(prefix2);
				prefix4.setNested(true);
				prefix3.getSubNets().add(prefix4);
				prefix5.setNested(true);
				prefix3.getSubNets().add(prefix5);
				prefix3.setNested(true);
				prefix.getSubNets().add(prefix3);
				repo.save(prefix);*/
				repo.save(prefix1);
				
				
				
			
				
				/*MerchandiseEntity pants = new MerchandiseEntity(
						  new Double(34.99), "Pair of Pants");
				repo.save(pants);*/
			}
		};
	  }

}
