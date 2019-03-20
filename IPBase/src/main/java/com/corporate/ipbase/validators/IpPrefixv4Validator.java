package com.corporate.ipbase.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.corporate.ipbase.domain.IpPrefixv4;

@Component
public class IpPrefixv4Validator implements Validator  {{

}

@Override
public boolean supports(Class<?> clazz) {
	// TODO Auto-generated method stub
	return IpPrefixv4.class.isAssignableFrom(clazz);
}

@Override
public void validate(Object target, Errors errors) {
	System.out.println("@@@@@ wywołanie validate");
	IpPrefixv4 prefix = (IpPrefixv4)target;
	String regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\/(3[0-2]|[1-2][0-9]|[1-9])";
	if(!prefix.getPrefix().matches(regex))
	errors.rejectValue("Prefix", "", "Bad prefix");
	
}
	
}
