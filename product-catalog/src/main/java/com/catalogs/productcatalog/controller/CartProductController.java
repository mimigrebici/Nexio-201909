package com.catalogs.productcatalog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.catalogs.productcatalog.dto.CartProductDTO;
import com.catalogs.productcatalog.exceptions.CustomException;
import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.service.ICartService;

@RestController
public class CartProductController {

	@Autowired
	private ICartService cartService;


	@PostMapping("/cart/{productRef}/add")
	public ResponseEntity<Void> addProductToCart(@PathVariable("productRef") String productRef){
		try {
			
			cartService.addProductToCart(productRef, UserController.getUserName());

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

			return ResponseEntity.created(uri).build();
		}catch(Exception ex) {
			return null;
		}
	}

	
	@PostMapping("/cart/{productRef}/remove")
	public ResponseEntity<Void> removeProductToCart(@PathVariable("productRef") String productRef){
		try {
			cartService.removeProductFromCart(productRef, UserController.getUserName());

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

			return ResponseEntity.created(uri).build();
		}catch(Exception ex) {
			return null;
		}
	}
	
	
	@GetMapping("/cart/products")
	public List<CartProductDTO> getUserCart() {
		try {
			List<CartProductDTO> cartProducts = cartService.getAllCartProducts(UserController.getUserName());

			if(cartProducts != null && !cartProducts.isEmpty()) {
				return cartProducts;
			}

			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		}catch(EntityNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		} catch (CustomException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
