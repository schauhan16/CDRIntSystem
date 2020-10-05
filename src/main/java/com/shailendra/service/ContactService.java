package com.shailendra.service;

import com.shailendra.dto.ContactDTO;
import com.shailendra.pojo.Contact;
import com.shailendra.repo.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ContactRepository contactRepository;

    public ContactDTO getContact(long phoneNumber){
        return convertToDTO(contactRepository.findByNumber(phoneNumber));
    }

    public Contact getContactEntity(long phoneNumber){
        return contactRepository.findByNumber(phoneNumber);
    }

    public ContactDTO saveContact(ContactDTO contact){
        return convertToDTO(contactRepository.save(convertToEntity(contact)));
    }

    public Contact saveContact(Contact contact){
        return contactRepository.save(contact);
    }

    public List<ContactDTO> saveContact(List<ContactDTO> contacts){
        List<Contact> savedEntities = (List<Contact>) contactRepository.saveAll(contacts.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        return savedEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteContactByNumber(long number){
        contactRepository.deleteByNumber(number);
    }

    public List<ContactDTO> getAllContact(){
        return contactRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Contact convertToEntity(ContactDTO contactDTO) {
        return modelMapper.map(contactDTO, Contact.class);
    }

    private ContactDTO convertToDTO(Contact contact){
        return modelMapper.map(contact, ContactDTO.class);
    }
}
