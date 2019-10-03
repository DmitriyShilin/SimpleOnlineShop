package com.mycompany.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mycompany.model.UserMatchingPassword;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
	
	@Override	
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserMatchingPassword user = (UserMatchingPassword) value;		
		boolean match = user.getPassword().equals(user.getMatchingPassword());
		if(!match) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Passwords do not match.").addPropertyNode("matchingPassword").addConstraintViolation();
		}	
		return match;
	}
}
