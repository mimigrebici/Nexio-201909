package com.catalogs.productcatalog.exceptions;

/**
 * Custum Exception : entity not found cases
 * 
 * @author MimiGrebici
 *
 */
public class EntityNotFoundException  extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9155252289733439971L;

	
	public EntityNotFoundException(String message) {
		super(message);
	}



}
