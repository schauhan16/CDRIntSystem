package com.shailendra.pojo;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {

	private String name;

	private String email;

	@Id
	@NonNull
	private Long number;

	public Contact() {
	}

	public Contact(Long number) {
		this.number = number;
	}

	public Contact(String name, String email, Long number) {
		this.name = name;
		this.email = email;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Contact{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", number=" + number +
				'}';
	}
}
