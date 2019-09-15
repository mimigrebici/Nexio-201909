package com.catalogs.productcatalog.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogs.productcatalog.jpa.entity.CartProductEntity;

public interface CartProductDAO extends JpaRepository<CartProductEntity, Long> {
	
}