package com.shailendra.service;

import com.shailendra.dto.ContactDTO;
import com.shailendra.main.CDRSystemApplication;
import com.shailendra.pojo.Contact;
import com.shailendra.repo.ContactRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(classes = CDRSystemApplication.class)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ContactServiceTest {
    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @After
    public void cleanup(){
        contactRepository.deleteAll();
    }

    private List<ContactDTO> createMultipleContacts(){
        return IntStream.range(0, 10)
                .mapToObj(i -> createContact())
                .collect(Collectors.toList());
    }

    private ContactDTO createContact(){
        ContactDTO contact = new ContactDTO();
        contact.setNumber(new Random().nextLong());
        return contact;
    }

    @Test
    public void testSavingSingleContact(){
        ContactDTO contact = createContact();

        ContactDTO savedContact = contactService.saveContact(contact);
        Assert.assertEquals(contact, savedContact);

        Assert.assertEquals(1, contactService.getAllContact().size());
    }

    @Test
    public void testSavingMultipleContact(){
        List<ContactDTO> contacts = createMultipleContacts();

        System.out.println(contacts.toString());

        contactService.saveContact(contacts);
        Assert.assertEquals(contacts, contactService.getAllContact());

        Assert.assertEquals(10, contactService.getAllContact().size());
    }

    @Test
    public void testDeletionContact(){
        List<ContactDTO> contacts = createMultipleContacts();

        contactService.saveContact(contacts);
        Assert.assertEquals(contacts, contactService.getAllContact());

        Assert.assertEquals(10, contactService.getAllContact().size());

        contactService.deleteContactByNumber(contacts.get(0).getNumber());
        Assert.assertEquals(9, contactService.getAllContact().size());
    }

    @Test
    public void testUpdateContact(){
        List<ContactDTO> contacts = createMultipleContacts();

        contactService.saveContact(contacts);
        Assert.assertEquals(contacts, contactService.getAllContact());

        Assert.assertEquals(10, contactService.getAllContact().size());

        String name = "Shailendra";
        ContactDTO toUpdate = contacts.get(0);
        toUpdate.setName(name);
        contactService.saveContact(toUpdate);
        Assert.assertEquals(10, contactService.getAllContact().size());

        Assert.assertEquals(toUpdate, contactService.getContact(toUpdate.getNumber()));
    }

    @Test(expected = Exception.class)
    public void throwExceptionIfNull(){
        Contact contact = new Contact();

        contactService.saveContact(contact);
    }
}
