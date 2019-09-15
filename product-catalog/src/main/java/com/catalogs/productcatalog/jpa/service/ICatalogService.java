package com.catalogs.productcatalog.jpa.service;

import java.util.Optional;

import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.entity.CatalogEntity;

/**
 * 
 * @author Mimi Grebici
 *
 */
public interface ICatalogService {

	/**
	 * Finds a catalog by its reference. Throws an EntityNotFoundException if the reference does not martch with any catalog.
	 * 
	 * @param String : the reference of the Catalog 
	 * @return CatalogEntity
	 * @throws EntityNotFoundException
	 */
	Optional<CatalogEntity> findByReference(String reference)  throws EntityNotFoundException ;

	/**
	 * Finds all catalogs 
	 * @return Iterable<CatalogEntity>
	 */
	Iterable<CatalogEntity> findAllCatalogs();
}
