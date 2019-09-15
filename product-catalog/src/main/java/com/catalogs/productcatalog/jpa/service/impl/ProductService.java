package com.catalogs.productcatalog.jpa.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.dao.ProductDAO;
import com.catalogs.productcatalog.jpa.entity.ProductEntity;
import com.catalogs.productcatalog.jpa.service.IProductService;

@Repository
@Transactional
public class ProductService implements IProductService{

	@Autowired
	private ProductDAO productDao;

	@Override
	public Optional<ProductEntity> findByReference(String reference) throws EntityNotFoundException {
		List<ProductEntity> list = productDao.findAll();
		Optional<ProductEntity> product = list.stream()
											.filter(p -> p.getReference().equals(reference))
											.findAny();

		if(product == null || !product.isPresent()) {
			throw new EntityNotFoundException("Product reference incorrect: Product Not found");
		}

		return product;
	}

}