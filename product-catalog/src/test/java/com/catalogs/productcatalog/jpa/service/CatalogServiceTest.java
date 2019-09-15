package com.catalogs.productcatalog.jpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.entity.CatalogEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceTest {

	@Autowired
	private ICatalogService catalogService;

	@Test
	public void testFindByReference() {
		try {
			Optional<CatalogEntity> catalog = catalogService.findByReference("BOOK-REF");

			assertTrue(catalog.isPresent());
			assertEquals("Books", catalog.get().getName());
		}catch(EntityNotFoundException ex) {
			fail(ex.getMessage());
		}
	}


	@Test
	public void testFindByReference_NoExistingRef() {

		try {
			catalogService.findByReference("NOT-EXIST-REF");
		}catch(Exception ex) {
			assertTrue(ex instanceof EntityNotFoundException);
		}

	}

}
