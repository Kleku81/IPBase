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
	//byte b = 2;
	System.out.println("Prefix = "+prefix);
	if (prefix.getBytes()[prefix.getBytes().length-1] != -1) {
		System.out.println("wystapił błąd bytes");
		errors.rejectValue("bytes", "", "Bytes error");
	}
}
	
}
