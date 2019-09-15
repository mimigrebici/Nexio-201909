package com.catalogs.productcatalog.jpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entity that represents a cart of products created by a user
 * 
 * @author Mimi Grebici: mimi.grebici@gmail.com
 *
 */
@Entity
public class CartEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Date of creation of the cart.
	 */
	private Date creationDate;
	
	/**
	 * Reference of the cart.
	 */
	private String reference;
	
	/**
	 * The user who created the cart.
	 */
	@OneToOne
	private UserEntity userEntity;
	
	protected CartEntity() {
	}
	
	public CartEntity(Date creationDate, String reference, UserEntity userEntity) {
		super();
		this.creationDate = creationDate;
		this.reference = reference;
		this.userEntity = userEntity;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public String toString() {
		return "CartEntity [creationDate=" + creationDate + ", reference=" + reference + ", userEntity=" + userEntity
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((userEntity == null) ? 0 : userEntity.hashCode());
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
		CartEntity other = (CartEntity) obj;
	    if (!reference.equals(other.reference))
			return false;
		return true;
	}

	
	
}
