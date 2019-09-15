package com.catalogs.productcatalog.jpa.service;

import java.util.Optional;

import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.entity.UserEntity;

public interface IUserService {

	/**
	 * Returns an Optional of UserEntity if the given userLogin exists, if not, an EntityNotFoundException is thrown
	 *  
	 * @param userLogin
	 * @return Optional<UserEntity>
	 * @throws EntityNotFoundException
	 */
	Optional<UserEntity> findByLogin(String userLogin) throws EntityNotFoundException;
		
}