package com.catalogs.productcatalog.dto;

import com.catalogs.productcatalog.jpa.entity.ProductEntity;
import com.catalogs.productcatalog.jpa.entity.UserEntity;

public class CartProductDTO {
	
	private UserEntity cartOwner;
	
	private ProductEntity product;
	
	private Long nbrPieces;

	public CartProductDTO(UserEntity cartOwner, ProductEntity product, Long nbrPieces) {
		super();
		this.cartOwner = cartOwner;
		this.product = product;
		this.nbrPieces = nbrPieces;
	}

	public UserEntity getCartOwner() {
		return cartOwner;
	}

	public void setCartOwner(UserEntity cartOwner) {
		this.cartOwner = cartOwner;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Long getNbrPieces() {
		return nbrPieces;
	}

	public void setNbrPieces(Long nbrPieces) {
		this.nbrPieces = nbrPieces;
	}
	
}
