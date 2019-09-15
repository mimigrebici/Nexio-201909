package com.catalogs.productcatalog.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 * Entity that represents an association between a cart and a product with the number of pieces of the product.
 * 
 * @author Mimi Grebici: mimi.grebici@gmail.com
 *
 */

@Entity
public class CartProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Product added to the cart
	 */
	@ManyToOne
	private ProductEntity products;
	
	/**
	 * Cart .
	 */
	@ManyToOne
	private CartEntity cartEntity;
	
	
	
	protected CartProductEntity() {
	}
	
	public CartProductEntity(ProductEntity products, CartEntity cartEntity) {
		super();
		this.products = products;
		this.cartEntity = cartEntity;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ProductEntity getProducts() {
		return products;
	}


	public void setProducts(ProductEntity products) {
		this.products = products;
	}


	public CartEntity getCartEntity() {
		return cartEntity;
	}


	public void setCartEntity(CartEntity cartEntity) {
		this.cartEntity = cartEntity;
	}


	@Override
	public String toString() {
		return "CartProductEntity [products=" + products + ", cartEntity=" + cartEntity + "]";
	}
	
	

}
