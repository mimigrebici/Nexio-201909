package com.catalogs.productcatalog;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.catalogs.productcatalog.jpa.controller.intergration.test.CatalogProductControllerIT;
import com.catalogs.productcatalog.jpa.service.CartServiceTest;
import com.catalogs.productcatalog.jpa.service.CatalogServiceTest;
import com.catalogs.productcatalog.jpa.service.ProductServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ CatalogServiceTest.class, ProductServiceTest.class, CartServiceTest.class, CatalogProductControllerIT.class })
public class AllTests {

}
