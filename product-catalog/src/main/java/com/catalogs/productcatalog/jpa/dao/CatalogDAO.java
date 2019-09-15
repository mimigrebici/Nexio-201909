package com.catalogs.productcatalog.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogs.productcatalog.jpa.entity.CatalogEntity;

public interface CatalogDAO extends JpaRepository<CatalogEntity, Long> {
	
}