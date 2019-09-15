package com.catalogs.productcatalog.jpa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.dao.CatalogDAO;
import com.catalogs.productcatalog.jpa.entity.CatalogEntity;
import com.catalogs.productcatalog.jpa.service.ICatalogService;

@Service
public class CatalogService implements ICatalogService{
		
	@Autowired
	private CatalogDAO catalogDao;
	
	@Override
	public Iterable<CatalogEntity> findAllCatalogs() {
		return catalogDao.findAll();
	}
	
	@Override
	public Optional<CatalogEntity> findByReference(String reference) throws EntityNotFoundException {
		
		Example<CatalogEntity> example = Example.of(new CatalogEntity(reference, null, null, false, null) );
		List<CatalogEntity> list = catalogDao.findAll(example);
		
		if(list == null || list.isEmpty()) {
			throw new EntityNotFoundException("Catalog reference incorrect: Catalog Not found");
		}
		
		return Optional.ofNullable(list.get(0));
	}
}