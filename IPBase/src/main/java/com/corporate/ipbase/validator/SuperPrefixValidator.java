package com.corporate.ipbase.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.corporate.ipbase.domain.IpPrefixv4;
import com.corporate.ipbase.service.IpPrefixv4Service;

@Component
public class SuperPrefixValidator implements ConstraintValidator<SuperPrefixConstraint, IpPrefixv4> {
	
	IpPrefixv4Service prefixService; 
	
	
    public SuperPrefixValidator(IpPrefixv4Service prefixService) {
		super();
		this.prefixService = prefixService;
	}

	@Override
    public void initialize(SuperPrefixConstraint superPrefix) {
    }

    @Override
    public boolean isValid(IpPrefixv4 prefix, ConstraintValidatorContext cxt) {
    	
    	System.out.println("!!!!!!Validacja!!!!!");
        
    	return !(prefixService.checkExistance(prefix).isPresent()); 		
}

}
