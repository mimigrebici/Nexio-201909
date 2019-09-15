package com.catalogs.productcatalog.exceptions;

/**
 * A custom error Class
 * 
 * @author Mimi Grebici
 *
 */
public class CustomException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1110904131132451716L;
	
	private String message;
	private String errorDetails;
	
	
	public CustomException( String message, String errorDetails) {
		super();
		this.message = message;
		this.errorDetails = errorDetails;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getErrorDetails() {
		return errorDetails;
	}


	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
}
