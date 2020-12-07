package com.cg.corona.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author HARSHITA AGARWAL
 * check the patient id contain integer value if we are inserting string value they give exception
 *
 */
public class ServiceValidation {
	public boolean validatePatientId(int patientId) throws ServiceNotFoundException {
		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = null;
		matcher = pattern.matcher(String.valueOf(patientId));
		return matcher.matches();
	}

}
