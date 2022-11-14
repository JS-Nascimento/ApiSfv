package dev.jstec.apisfv.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import dev.jstec.apisfv.validation.constraintvalidation.NotEmptyListValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy =  NotEmptyListValidator.class)
public @interface NotEmptyList {
	String message() default "Products order list cannot be empty.";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
