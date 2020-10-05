package com.shailendra.service;

import com.shailendra.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhoneDirectoryService {

	@Autowired
	private ContactService contactService;

	public ContactDTO addEntry(ContactDTO contact){
		return contactService.saveContact(contact);
	}

	@Transactional
	public void deleteEntry(long phoneNumber){
		contactService.deleteContactByNumber(phoneNumber);
	}

	public ContactDTO getEntry(long phoneNumber){
		return contactService.getContact(phoneNumber);
	}

	public List<ContactDTO> getAllEntries() {
		return contactService.getAllContact();
	}

	public List<ContactDTO> bulkInsert(List<ContactDTO> records) {
		return contactService.saveContact(records);
	}
}
