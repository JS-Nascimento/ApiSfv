package dev.jstec.apisfv.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dev.jstec.apisfv.validation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
	
		return value != null && !value.isEmpty();
	}

}
