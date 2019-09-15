package com.catalogs.productcatalog.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogs.productcatalog.jpa.entity.CartEntity;

public interface CartDAO extends JpaRepository<CartEntity, Long> {
	
}