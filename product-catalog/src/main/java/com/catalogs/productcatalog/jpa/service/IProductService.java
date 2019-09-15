package com.catalogs.productcatalog.jpa.service;

import java.util.Optional;

import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.entity.ProductEntity;

public interface IProductService {
		
	/**
	 * Finds a product by its reference.
	 * @param String: the reference of the product
	 * @return Optional<ProductEntity>
	 * @throws EntityNotFoundException
	 */
	public Optional<ProductEntity> findByReference(String reference)  throws EntityNotFoundException ;

}