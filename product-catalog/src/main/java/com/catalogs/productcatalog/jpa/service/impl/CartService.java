package com.catalogs.productcatalog.jpa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.catalogs.productcatalog.dto.CartProductDTO;
import com.catalogs.productcatalog.exceptions.CustomException;
import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.dao.CartDAO;
import com.catalogs.productcatalog.jpa.dao.CartProductDAO;
import com.catalogs.productcatalog.jpa.entity.CartEntity;
import com.catalogs.productcatalog.jpa.entity.CartProductEntity;
import com.catalogs.productcatalog.jpa.entity.ProductEntity;
import com.catalogs.productcatalog.jpa.entity.UserEntity;
import com.catalogs.productcatalog.jpa.service.ICartService;
import com.catalogs.productcatalog.jpa.service.IProductService;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartDAO cartDao;

	@Autowired
	private CartProductDAO cartProductDao;

	@Autowired
	private IProductService productService;

	@Autowired
	private UserService userService;

	@Override
	public Optional<CartEntity> findCartByUserLogin(String login) throws EntityNotFoundException {
		// 1- test if a user exists with the given login, if not throw an exception with the corresponding message
		Optional<UserEntity> user = userService.findByLogin(login);

		// 2- test if any cart already created to the user, if not, return an empty Optional
		Example<CartEntity> example2 = Example.of(new CartEntity(null, null, user.get()) );
		List<CartEntity> carts = cartDao.findAll(example2);

		if(carts == null || carts.isEmpty()) {
			return Optional.empty();
		}

		return Optional.ofNullable(carts.get(0));

	}

	@Override
	public Optional<CartEntity> createCart(String userLogin) throws EntityNotFoundException {
		// test if the user has already a cart, if yes return that cart, if not, create a new one and return it

		Optional<CartEntity> cart = findCartByUserLogin(userLogin);
		if(cart.isPresent()) {
			return cart;
		}

		Optional<UserEntity> user = userService.findByLogin(userLogin);
		Date date = new Date();
		CartEntity newCart = cartDao.save(new CartEntity(date, user.get().getLogin() + "-" + date.getTime(), user.get()));

		return Optional.ofNullable(newCart);

	}


	@Override
	@Transactional
	public Optional<CartProductEntity> addProductToCart(String productRef, String userLogin) throws EntityNotFoundException, CustomException{

		// test if the productRef exists, if not, the service throws an EntityNotFoundException
		Optional<ProductEntity> product = productService.findByReference(productRef);

		// test if the user has already a cart (the service throws an Exception if the user doesn't exist
		Optional<CartEntity> cart = findCartByUserLogin(userLogin);

		// if the user doesn't have a cart, create a new one
		if(!cart.isPresent()) {
			cart = createCart(userLogin);
		}
		
		CartProductEntity cartProduct = new CartProductEntity(product.get(), cart.get());
		cartProduct = cartProductDao.save(cartProduct);

		if(cartProduct == null) {
			throw new CustomException("CartProductEntity has not been created", "CartProductEntity has not been created at CartService.addProductToCart");
		}

		return Optional.of(cartProduct);

	}


	@Override
	@Transactional
	public void removeProductFromCart(String productRef, String userLogin) throws EntityNotFoundException, CustomException  {
		// test if the productRef exists, if not, the service throws an EntityNotFoundException
		Optional<ProductEntity> productToRemove = productService.findByReference(productRef);

		// test if the user has already a cart (the service throws an Exception if the user doesn't exist)
		Optional<CartEntity> cart = findCartByUserLogin(userLogin);

		// Throw an EntityNotFoundException if there is no cart for the user
		if(cart == null || !cart.isPresent()) {
			throw new EntityNotFoundException("No cart registred for the user");
		}

		Example<CartProductEntity> example = Example.of(new CartProductEntity(null, cart.get()) );
		List<CartProductEntity> cartProductList = cartProductDao.findAll(example);

		// if a cart is registred and no cartProduct, delete the cart
		if(cartProductList == null || cartProductList.isEmpty()) {
			cartDao.delete(cart.get()); findCartByUserLogin(userLogin);
			return;
		}

		// if the product does not belong to the product list, throw a CustomException
		Optional<CartProductEntity> cartProductToRemove = cartProductList.stream()
																			.filter(cartProduct -> cartProduct.getProducts().equals(productToRemove.get()))
																			.findAny();
		
		if(!cartProductToRemove.isPresent()) {
			throw new CustomException("The product "+ productRef + " doesn't belong to the user cart",
					"The product "+ productRef + " is not a product added by the user "+userLogin+" to the cart.");
		}

		cartProductDao.delete(cartProductToRemove.get());
		
		// if the product is the only product in the products list, delete the cart
		if(cartProductList != null && cartProductList.size() == 1) {
			cartDao.delete(cart.get());
			return;
		}

	}

	@Override
	public List<CartProductDTO> getAllCartProducts(String userLogin) throws EntityNotFoundException, CustomException{
		Optional<CartEntity> cart = findCartByUserLogin(userLogin);

		// Throw an EntityNotFoundException if there is no cart for the user
		if(cart == null || !cart.isPresent()) {
			throw new EntityNotFoundException("No cart registred for the user");
		}

		Example<CartProductEntity> example = Example.of(new CartProductEntity(null, cart.get()) );
		List<CartProductEntity> cartProductList = cartProductDao.findAll(example);
		
		List<CartProductDTO> allCartProducts = new ArrayList<>();
		
		if(cartProductList != null && !cartProductList.isEmpty()) {
			// get a Map of all the products in the list and the count of each product
			Map<ProductEntity, Long> cartContent = cartProductList.stream()
													   .collect(Collectors.groupingBy((cartProd -> cartProd.getProducts()), Collectors.counting()));
			
			Optional<UserEntity> user= userService.findByLogin(userLogin);
			
			cartContent.entrySet().stream()
			   .forEach(product -> allCartProducts.add(new CartProductDTO(user.get(), product.getKey(), cartContent.get(product.getKey()))));
			
		}
		
		return allCartProducts;
	}

}
