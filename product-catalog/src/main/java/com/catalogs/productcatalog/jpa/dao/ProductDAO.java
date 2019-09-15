package com.catalogs.productcatalog.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogs.productcatalog.jpa.entity.ProductEntity;

public interface ProductDAO extends JpaRepository<ProductEntity, Long> {
	
}