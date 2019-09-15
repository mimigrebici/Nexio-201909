package com.catalogs.productcatalog.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity that represents a specific product of a catalog.
 * 
 * @author Mimi Grebici: mimi.grebici@gmail.com
 * 
 */
@Entity
public class ProductEntity {
	
	@Id
	private Long id;
	
	
	/**
	 * Description of the product.
	 */
	private String description;
	
	/**
	 * Name of the product.
	 */
	private String name;
	
	/**
	 * Whether or not the product is available in stock.
	 */
	private boolean available;
	
	/**
	 * The price of the product.
	 */
	private Float price;
	
	/**
	 * picture name of the product.
	 */
	private String picture;
	
	/**
	 * Reference of the product.
	 */
	private String reference;
	
	protected ProductEntity() {
		
	}
	
	public ProductEntity(String description, String name, boolean available, Float price, String picture,
			String reference) {
		super();
		this.description = description;
		this.name = name;
		this.available = available;
		this.price = price;
		this.picture = picture;
		this.reference = reference;
	}
	
	public ProductEntity(Long id, String description, String name, boolean available, Float price, String picture,
			String reference) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.available = available;
		this.price = price;
		this.picture = picture;
		this.reference = reference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "ProductEntity [description=" + description + ", name=" + name + ", available=" + available + ", price="
				+ price + ", picture=" + picture + ", reference=" + reference 
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

	
}
