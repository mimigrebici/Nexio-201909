package com.catalogs.productcatalog.jpa.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.dao.UserDAO;
import com.catalogs.productcatalog.jpa.entity.UserEntity;
import com.catalogs.productcatalog.jpa.service.IUserService;

@Repository
@Transactional
public class UserService implements IUserService{

	@Autowired
	private UserDAO userDao;

	@Override
	public Optional<UserEntity> findByLogin(String userLogin) throws EntityNotFoundException {
		// 1- test if a user exists with the given login, if not throw an exception with the corresponding message
		Example<UserEntity> example1 = Example.of(new UserEntity(null, null, null, null, userLogin) );
		List<UserEntity> list = userDao.findAll(example1);

		if(list == null || list.isEmpty()) {
			throw new EntityNotFoundException("No User registred with the given login");
		}
		UserEntity user = list.get(0);
		
		return Optional.of(user);
	}


}