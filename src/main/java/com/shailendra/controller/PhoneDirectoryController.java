package com.shailendra.controller;

import com.shailendra.dto.ContactDTO;
import com.shailendra.service.PhoneDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phoneDirectory")
public class PhoneDirectoryController {

	@Autowired
	private PhoneDirectoryService phoneDirectoryService;

	@GetMapping
	public ContactDTO getContact(@RequestParam(value = "phoneNumber") long phoneNumber) {
		return phoneDirectoryService.getEntry(phoneNumber);
	}

	@GetMapping("all")
	public List<ContactDTO> getAllContacts() {
		return phoneDirectoryService.getAllEntries();
	}

	@PostMapping
	@ResponseBody
	public ContactDTO addPhoneBookEntry(@RequestBody ContactDTO contact){
		return phoneDirectoryService.addEntry(contact);
	}

	@GetMapping("deleteEntry")
	public void deleteEntry(@RequestParam(value="phoneNumber") long phoneNumber){
		phoneDirectoryService.deleteEntry(phoneNumber);
	}

	@PostMapping("bulkInsert")
	@ResponseBody
	public List<ContactDTO> bulkInsert(@RequestBody List<ContactDTO> records) {
		return phoneDirectoryService.bulkInsert(records);
	}

}
