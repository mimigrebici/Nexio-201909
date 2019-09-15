package com.catalogs.productcatalog.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity that represents a user of the application
 * @author Mimi Grebici: mimi.grebici@gmail.com
 *
 */
@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * firstname.
	 */
	private String firstname;
	
	/**
	 * Lastname.
	 */
	private String lastname;
	
	/**
	 * Phone number.
	 */
	private String phone;
	
	/**
	 * Email.
	 */
	private String email;
	
	/**
	 * Login.
	 */
	private String login;
	
	/**
	 * Password of the user.
	 */
//	private PasswordEntity passwordEntity;
	
	protected UserEntity() {
		
	}
	
	public UserEntity(String firstname, String lastname, String phone, String email, String login) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	

//	public PasswordEntity getPasswordEntity() {
//		return passwordEntity;
//	}
//
//	public void setPasswordEntity(PasswordEntity passwordEntity) {
//		this.passwordEntity = passwordEntity;
//	}

	@Override
	public String toString() {
		return "UserEntity [firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", email=" + email
				+ ", login=" + login + "]";
	}
	
}
