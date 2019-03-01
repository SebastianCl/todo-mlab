package com.apress.mlab.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ToDoValidationErrorBuilder {
	public static ToDoValidationError fromBindingErrors(Errors errors) {
		ToDoValidationError error = new ToDoValidationError("Validationfailed. " + errors.getErrorCount() + " error(s)");
		for (ObjectError objectError : errors.getAllErrors()) {
			error.addValidationError(objectError.getDefaultMessage());
		}
		return error;
	}
}