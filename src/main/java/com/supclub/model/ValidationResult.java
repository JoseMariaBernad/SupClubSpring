package com.supclub.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
	private List<String> errors;
	private boolean isValid;
	
	public ValidationResult() {
		errors = new ArrayList<String>();
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
