package com.catalogs.productcatalog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.entity.CatalogEntity;
import com.catalogs.productcatalog.jpa.entity.ProductEntity;
import com.catalogs.productcatalog.jpa.service.ICatalogService;
import com.catalogs.productcatalog.jpa.service.IProductService;

/**
 * Controller of APIs about Catalog and Products
 * 
 * @author Mimi Grebici: mimi.grebici@gmail.com
 *
 */

@RestController
public class CatalogProductController {

	@Autowired
	private ICatalogService catalogService;

	@Autowired
	private IProductService productService;
	

	/**
	 * Get a catalog of products
	 * 
	 * @return CatalogEntity
	 */
	@GetMapping("/catalogs/{catalogRef}")
	public CatalogEntity getCatalog(@PathVariable(name = "catalogRef") String catalogReference) {
		try {
			Optional<CatalogEntity> catalog = catalogService.findByReference(catalogReference);

			if(catalog.isPresent()) {
				return catalog.get();
			}

			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		}catch(EntityNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}


	@GetMapping("/products/{productRef}")
	public ProductEntity  getProductDetails(@PathVariable(name = "productRef") String productReference) {

		try {
			Optional<ProductEntity> product = productService.findByReference(productReference);

			if(product.isPresent()) {
				return product.get();
			}

			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		}catch(EntityNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	

}
