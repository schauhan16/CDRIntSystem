package com.shailendra.pojo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneDirectory {

	private List<Contact> entries;

	public PhoneDirectory() {
		this.entries = new ArrayList<>();
	}

	public PhoneDirectory(List<Contact> contacts) {
		super();
		this.entries = contacts;
	}

	public List<Contact> getRecords() {
		return entries;
	}

	public void setRecords(List<Contact> contacts) {
		this.entries = contacts;
	}

}
