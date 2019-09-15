package com.catalogs.productcatalog.jpa.service;

import java.util.List;
import java.util.Optional;

import com.catalogs.productcatalog.dto.CartProductDTO;
import com.catalogs.productcatalog.exceptions.CustomException;
import com.catalogs.productcatalog.exceptions.EntityNotFoundException;
import com.catalogs.productcatalog.jpa.entity.CartEntity;
import com.catalogs.productcatalog.jpa.entity.CartProductEntity;

public interface ICartService {

	/**
	 * Finds the user's Cart. Throws an EntityNotFoundException if no user exists with the given login
	 * 
	 * @param login of the user who created the cart
	 * @return cart: empty Optional if no cart registred for the user
	 * @throws EntityNotFoundException
	 */
	Optional<CartEntity> findCartByUserLogin(String login) throws EntityNotFoundException;
	
	/**
	 * Creates a new cart to the user corresponding to the given login. Throws an EntityNotFoundException if no user existes
	 *   with the given login. 
	 * @param userLogin
	 * @throws EntityNotFoundException
	 */
	Optional<CartEntity> createCart(String userLogin) throws EntityNotFoundException;

	/**
	 * Add a product, corresponding to the productRef,  to a cart, corresponding to the userLogin. 
	 * throws an EntityNotFoundException if the product or the user does not exist.
	 * throws CustomException for any particular Exception
	 * 
	 * @param productRef
	 * @param userLogin
	 * @return Optional<CartProductEntity>
	 * @throws EntityNotFoundException
	 * @throws CustomException
	 */
	Optional<CartProductEntity> addProductToCart(String productRef, String userLogin)
			throws EntityNotFoundException, CustomException;

	/**
	 * remove a product from the cart corresponding to the user
	 * 
	 * @param productRef
	 * @param userLogin
	 * @throws EntityNotFoundException
	 * @throws CustomException
	 */
	void removeProductFromCart(String productRef, String userLogin) throws EntityNotFoundException, CustomException;

	/**
	 * returns a Map containing all the products of the cart as the key, and the number of each of them in the cart.

	 * @param userLogin
	 * @return List<CartProductDTO> 
	 * @throws EntityNotFoundException
	 * @throws CustomException
	 */
	List<CartProductDTO> getAllCartProducts(String userLogin) throws EntityNotFoundException, CustomException;
}