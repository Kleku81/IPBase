package com.corporate.ipbase.processors;

import java.net.BindException;
import org.springframework.validation.*;

import org.hibernate.PropertyAccessException;

public class CustomBindingErrorProcessor{ //implements DefaultBindingErrorProcessor {

    /*public void processMissingFieldError(String missingField, BindException errors) {
        super.processMissingFieldError(missingField, errors);
    }

    public void processPropertyAccessException(PropertyAccessException accessException, BindException errors) {
        if(accessException.getCause() instanceof IllegalArgumentException)
            errors.rejectValue(accessException.getPropertyChangeEvent().getPropertyName(), "<SOME_SPECIFIC_CODE_IF_YOU_WANT>", accessException.getCause().getMessage());
        else
            defaultSpringBindingErrorProcessor.processPropertyAccessException(accessException, errors);
    }*/

}