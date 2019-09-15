package com.catalogs.productcatalog.jpa.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity that represents a catalog of products.
 * 
 * @author Mimi Grebici: mimi.grebici@gmail.com
 *
 */
@Entity
public class CatalogEntity {

	@Id
	@Column(nullable = false)
	private Long id;
	
	/**
	 * Reference of the catalog
	 */
	@Column(nullable = false)
	private String reference;

	/**
	 * Name of the catalog.
	 */
	@Column(nullable = false)
	private String name;
	
	
	/**
	 * A short description of the catalog.
	 */
	@Column(nullable = false)
	private String description;
	
	
	/**
	 * Whether or not the catalog is disabled. Default: false
	 */
	@Column(nullable = false)
	private boolean disabled;
	
	
	/**
	 * If the catalog has an expiration date. Null if it has not.
	 */
	@Column(nullable = true)
	private Date dateExp;
	
	
	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProductEntity> products;

	protected CatalogEntity() {
		
	}
	
	public CatalogEntity(String reference, String name, String description, boolean disabled, Date dateExp) {
		super();
		this.reference = reference;
		this.name = name;
		this.description = description;
		this.disabled = disabled;
		this.dateExp = dateExp;
	}
	
	public CatalogEntity(Long id,String reference, String name, String description, boolean disabled, Date dateExp) {
		super();
		this.id = id;
		this.reference = reference;
		this.name = name;
		this.description = description;
		this.disabled = disabled;
		this.dateExp = dateExp;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isDisabled() {
		return disabled;
	}


	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


	public Date getDateExp() {
		return dateExp;
	}


	public void setDateExp(Date dateExp) {
		this.dateExp = dateExp;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "CatalogEntity [reference=" + reference + ", name=" + name + ", description=" + description
				+ ", disabled=" + disabled + ", dateExp=" + dateExp 
				+ ", products=" + products + "]";
	}
	

}
