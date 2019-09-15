package com.catalogs.productcatalog.jpa.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.catalogs.productcatalog.dto.CartProductDTO;
import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.entity.CartEntity;
import com.catalogs.productcatalog.jpa.entity.CartProductEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {

	@Autowired
	private ICartService cartService;

	@Test
	public void testFindCartByUserLogin() {
		try {
			Optional<CartEntity> cart = cartService.findCartByUserLogin("user3");

			assertFalse(cart.isPresent());

		}catch(EntityNotFoundException ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testFindByReference_NoExistingUser() {
		try {
			cartService.findCartByUserLogin("NOT-EXIST-USER");
		}catch(Exception ex) {
			assertTrue(ex instanceof EntityNotFoundException);
		}
	}

	@Test
	public void testCreateCart() {
		try {
			Optional<CartEntity> cart = cartService.createCart("user4");

			assertTrue(cart.isPresent());
			
			cartService.removeProductFromCart("BK-HC-JAVA-126", "user4");
			
			Optional<CartEntity> cart2 = cartService.findCartByUserLogin("user4");
			assertFalse(cart2.isPresent());

		}catch(Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testCreateNewCartForSameUser() {
		try {
			Optional<CartEntity> cart1 = cartService.createCart("user1");

			assertTrue(cart1.isPresent());

			Optional<CartEntity> cart2 = cartService.createCart("user1");

			assertTrue(cart1.get().equals(cart2.get()));
			
			cartService.removeProductFromCart("BK-HC-JAVA-126", "user1");
			
		}catch(Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testAddProductToCart() {

		try {
			Optional<CartProductEntity> cartProduct1 = cartService.addProductToCart("BK-HC-JAVA-126", "user1");
			assertTrue(cartProduct1.isPresent());

			Optional<CartEntity> userCart1 = cartService.findCartByUserLogin("user1");
			assertTrue(userCart1.isPresent());

			Optional<CartProductEntity> cartProduct2 = cartService.addProductToCart("BK-HC-JAVA-MS-106", "user1");
			assertTrue(cartProduct2.isPresent());

			assertTrue(cartProduct1.get().getCartEntity().equals(cartProduct2.get().getCartEntity()));
			
		}catch(Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testRemoveProductFromCart() {
		try {
			Optional<CartProductEntity> cartProduct1 = cartService.addProductToCart("BK-HC-JAVA-126", "user3");
			assertTrue(cartProduct1.isPresent());

			Optional<CartEntity> userCart1 = cartService.findCartByUserLogin("user3");
			assertTrue(userCart1.isPresent());

			Optional<CartProductEntity> cartProduct2 = cartService.addProductToCart("BK-HC-JAVA-MS-106", "user3");
			assertTrue(cartProduct2.isPresent());

			cartService.removeProductFromCart("BK-HC-JAVA-MS-106", "user3");

			List<CartProductDTO> cartProducts = cartService.getAllCartProducts("user3");
			assertTrue(cartProducts.size() == 1);
			
			cartService.removeProductFromCart("BK-HC-JAVA-126", "user3");
			

		}catch(Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetAllCartProducts() {
		try {
			cartService.addProductToCart("BK-HC-JAVA-126", "user2");
			cartService.addProductToCart("BK-HC-JAVA-126", "user2");
			cartService.addProductToCart("BK-HC-JAVA-126", "user2");
			
			cartService.addProductToCart("BK-HC-JAVA-MS-106", "user2");
			cartService.addProductToCart("BK-HC-JAVA-MS-106", "user2");
			cartService.addProductToCart("BK-HC-JAVA-MS-106", "user2");
			cartService.addProductToCart("BK-HC-JAVA-MS-106", "user2");
			
			cartService.addProductToCart("LETSCOM-BT-HP-IPX7", "user2");
			
			List<CartProductDTO> cartProducts = cartService.getAllCartProducts("user2");
			
			assertTrue(cartProducts.stream().filter(cartProd -> cartProd.getProduct().getReference().equals("BK-HC-JAVA-126"))
			                                .anyMatch(elem -> elem.getNbrPieces() == 3));
			
			assertTrue(cartProducts.stream().filter(cartProd -> cartProd.getProduct().getReference().equals("BK-HC-JAVA-MS-106"))
											.anyMatch(elem -> elem.getNbrPieces() == 4));
			
			assertTrue(cartProducts.stream().filter(cartProd -> cartProd.getProduct().getReference().equals("LETSCOM-BT-HP-IPX7"))
                    						.anyMatch(elem -> elem.getNbrPieces() == 1));
			
		}catch(Exception ex) {
			fail(ex.getMessage());
		}
	}

}
