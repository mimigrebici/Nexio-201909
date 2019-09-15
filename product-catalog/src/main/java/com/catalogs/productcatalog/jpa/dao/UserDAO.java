package com.catalogs.productcatalog.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogs.productcatalog.jpa.entity.UserEntity;

public interface UserDAO extends JpaRepository<UserEntity, Long> {
	
}