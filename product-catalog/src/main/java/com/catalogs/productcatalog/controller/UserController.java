package com.catalogs.productcatalog.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserController {
	
	public static String getUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		
		return principal.toString();

	}

}
