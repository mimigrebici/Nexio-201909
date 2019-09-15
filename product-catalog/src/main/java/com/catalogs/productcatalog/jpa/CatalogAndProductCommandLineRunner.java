package com.catalogs.productcatalog.jpa;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.catalogs.productcatalog.jpa.dao.CatalogDAO;
import com.catalogs.productcatalog.jpa.dao.UserDAO;
import com.catalogs.productcatalog.jpa.entity.CatalogEntity;
import com.catalogs.productcatalog.jpa.entity.ProductEntity;
import com.catalogs.productcatalog.jpa.entity.UserEntity;

@Component
public class CatalogAndProductCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory
			.getLogger(CatalogAndProductCommandLineRunner.class);

	@Autowired
	private UserDAO userRepository;

	@Autowired
	private CatalogDAO catalogRepository;

	@Override
	public void run(String... args) throws Exception {

		//1- Create users
		userRepository.save(new UserEntity("User1","User1", null,null, "user1"));
		userRepository.save(new UserEntity("User2","User2", null,null, "user2"));
		userRepository.save(new UserEntity("User3","User3", null,null, "user3"));
		userRepository.save(new UserEntity("User4","User4", null,null, "user4"));

		// Print out all the users
		log.info("Users are.....");
		userRepository.findAll().forEach(user -> log.info(user.toString()));
		
		createCatalogsAndProducts();
		
		// print out all catalogs and their products
		log.info("Catalogs and products are.....");
		catalogRepository.findAll().forEach(catalog -> log.info(catalog.toString()));

	}


	private void createCatalogsAndProducts() {
		// Catalogs
		CatalogEntity booksCatalog = new CatalogEntity(1l, "BOOK-REF", "Books", "Books Catalog", false, null);
		CatalogEntity devicesCatalog = new CatalogEntity(2l, "DEVICE-REF", "Devices", "Electronic Devices Catalog", false, null);
		CatalogEntity musicCatalog = new CatalogEntity(3l, "MUSIC-REF", "Music", "Music Catalog", false, null);

		// Products
		ProductEntity product1 = new ProductEntity(1l,"All about Java Concurrency", "Java Concurrency in Practice", true, 35.26f, "/Java-conc-book.png",
				"BK-HC-JAVA-126");

		ProductEntity product2 = new ProductEntity(2l, "Mastering Microservices with Java: Build enterprise microservices with Spring Boot 2.0, Spring Cloud, and Angular, 3rd Edition",
				"Mastering Microservices with Java", true, 44.99f, "/Java-ms.png",
				"BK-HC-JAVA-MS-106");

		ProductEntity product3 = new ProductEntity(3l, "Head First Design Patterns: A Brain-Friendly Guide 1st Edition",
				"Head First Design Patterns", false, 14.39f, "/Java-dp.png",
				"BK-HC-JAVA-DP-006");

		ProductEntity product4 = new ProductEntity(4l, "LISEN iPhone Charger Cable 6ft, [Apple MFi Certified] Lightning Cable, Nylon Braided USB Fast Charging Cord Compatible with iPhone X/Xs Max/XR / 8/8 Plus / 7/7 Plus iPad (Grey)", 
				"LISEN iPhone Charger Cable 6ft", true, 15.99f, "/LISEN-iPhone-Charger-6ft.png",
				"LISEN-IP-CG-6FT");

		ProductEntity product5 = new ProductEntity(5l, "LETSCOM Bluetooth Headphones IPX7 Waterproof, Wireless Sport Earphones, HiFi Bass Stereo Sweatproof Earbuds w/Mic, Noise Cancelling Headset for Workout, Running, Gym, 8 Hours Play Time", 
				"LETSCOM Bluetooth Headphones IPX7", true, 21.99f, "/LETSCOM-Bluetooth-Headphones-IPX7.png",
				"LETSCOM-BT-HP-IPX7");

		ProductEntity product6 = new ProductEntity(6l, "Queen. The Platinum Collection: Greatest Hits I, II & III",
				"Queen. Greatest Hits", true, 13.99f, "/CD-queen-greatest-1-2-3.png",
				"QUEEN-GH-123");

		ProductEntity product7 = new ProductEntity(7l, "Pink Floyd: The Dark Side of the Moon LP (12\" album, 33 rpm)",
				"The Dark Side of the Moon LP", true, 22.99f, "/CD-pinkf-dsm.png",
				"PINK-FD-DSM-5698");


		List<ProductEntity> listProducts1 = new ArrayList<ProductEntity>();
		listProducts1.add(product1);
		listProducts1.add(product2);
		listProducts1.add(product3);

		booksCatalog.setProducts(listProducts1);
		catalogRepository.save(booksCatalog);

		List<ProductEntity> listProducts2 = new ArrayList<ProductEntity>();
		listProducts2.add(product4);
		listProducts2.add(product5);

		devicesCatalog.setProducts(listProducts2);
		catalogRepository.save(devicesCatalog);

		List<ProductEntity> listProducts3 = new ArrayList<ProductEntity>();
		listProducts3.add(product6);
		listProducts3.add(product7);

		musicCatalog.setProducts(listProducts3);
		catalogRepository.save(musicCatalog);

	}

}