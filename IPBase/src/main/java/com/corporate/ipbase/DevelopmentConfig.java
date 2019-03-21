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
				
				IpPrefixv4 prefix1 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"2.2.0.0/16","4","prefix1");
				prefix1.prefixToBytesMask();
				IpPrefixv4 prefix11 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",true,"2.2.2.0/24","4","prefix11");
				prefix11.prefixToBytesMask();
				prefix1.getSubNets().add(prefix11);
				IpPrefixv4 prefix111 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",true,"2.2.2.0/23","4","prefix11");
				prefix111.prefixToBytesMask();
				prefix11.getSubNets().add(prefix111);
				IpPrefixv4 prefix2 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"3.3.0.0/16","4","prefix2");
				prefix2.prefixToBytesMask();
				IpPrefixv4 prefix3 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"4.4.0.0/16","4","prefix3");
				prefix3.prefixToBytesMask();
				IpPrefixv4 prefix4 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"5.5.0.0/16","4","prefix4");
				prefix4.prefixToBytesMask();
				IpPrefixv4 prefix5 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"6.6.0.0/16","4","prefix5");
				prefix5.prefixToBytesMask();
				IpPrefixv4 prefix6 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"7.7.0.0/16","4","prefix6");
				prefix6.prefixToBytesMask();
				IpPrefixv4 prefix7 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"8.8.0.0/16","4","prefix7");
				prefix7.prefixToBytesMask();
				IpPrefixv4 prefix8 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"9.9.0.0/16","4","prefix8");
				prefix8.prefixToBytesMask();
				IpPrefixv4 prefix9 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"10.10.0.0/16","4","prefix9");
				prefix9.prefixToBytesMask();
				IpPrefixv4 prefix10 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"11.11.0.0/16","4","prefix10");
				prefix10.prefixToBytesMask();
				IpPrefixv4 prefix112 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"12.12.0.0/16","4","prefix11");
				prefix112.prefixToBytesMask();
				IpPrefixv4 prefix12 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"13.13.0.0/16","4","prefix12");
				prefix12.prefixToBytesMask();
				IpPrefixv4 prefix13 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"14.14.0.0/16","4","prefix13");
				prefix13.prefixToBytesMask();
				IpPrefixv4 prefix14 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"15.15.0.0/16","4","prefix14");
				prefix14.prefixToBytesMask();
				IpPrefixv4 prefix15 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"16.16.0.0/16","4","prefix15");
				prefix15.prefixToBytesMask();
				IpPrefixv4 prefix16 =  new IpPrefixv4(null,LocalDateTime.now(),LocalDateTime.now(),"5617","VRF-TEST",false,"17.17.0.0/16","4","prefix16");
				prefix16.prefixToBytesMask();
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
				repo.save(prefix2);
				repo.save(prefix3);
				repo.save(prefix4);
				repo.save(prefix5);
				repo.save(prefix6);
				repo.save(prefix7);
				repo.save(prefix8);
				repo.save(prefix9);
				repo.save(prefix10);
				repo.save(prefix11);
				repo.save(prefix12);
				repo.save(prefix13);
				repo.save(prefix14);
				repo.save(prefix15);
				repo.save(prefix16);
				
				
				
				
			
				
				/*MerchandiseEntity pants = new MerchandiseEntity(
						  new Double(34.99), "Pair of Pants");
				repo.save(pants);*/
			}
		};
	  }

}
