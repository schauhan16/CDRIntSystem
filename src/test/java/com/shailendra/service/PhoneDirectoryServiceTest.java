package com.shailendra.service;

import com.shailendra.dto.ContactDTO;
import com.shailendra.main.CDRSystemApplication;
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
public class PhoneDirectoryServiceTest {

    @Autowired
    private PhoneDirectoryService phoneDirectoryService;

    @Autowired
    private ContactRepository entryRepository;

    @After
    public void tearDown() {
        entryRepository.deleteAll();
    }

    private List<ContactDTO> createMultipleEntries(){
        return IntStream.range(0, 10)
                .mapToObj(i -> createEntry())
                .collect(Collectors.toList());
    }

    private ContactDTO createEntry(){
        ContactDTO entry = new ContactDTO();
        entry.setNumber(new Random().nextLong());
        return entry;
    }

    @Test
    public void testSavingSingleEntry(){
        ContactDTO entry = createEntry();

        ContactDTO savedEntry = phoneDirectoryService.addEntry(entry);
        Assert.assertEquals(entry, savedEntry);

        Assert.assertEquals(1, phoneDirectoryService.getAllEntries().size());
    }

    @Test
    public void testSavingMultipleEntry(){
        List<ContactDTO> entries = createMultipleEntries();

        List<ContactDTO> savedEntities = phoneDirectoryService.bulkInsert(entries);
        Assert.assertEquals(entries, savedEntities);

        Assert.assertEquals(10, phoneDirectoryService.getAllEntries().size());
    }

    @Test
    public void testDeletionEntry(){
        List<ContactDTO> entries = createMultipleEntries();

        phoneDirectoryService.bulkInsert(entries);
        Assert.assertEquals(entries, phoneDirectoryService.getAllEntries());

        Assert.assertEquals(10, phoneDirectoryService.getAllEntries().size());

        phoneDirectoryService.deleteEntry(entries.get(0).getNumber());
        Assert.assertEquals(9, phoneDirectoryService.getAllEntries().size());
    }

    @Test
    public void testUpdateEntry(){
        List<ContactDTO> entries = createMultipleEntries();

        phoneDirectoryService.bulkInsert(entries);
        Assert.assertEquals(entries, phoneDirectoryService.getAllEntries());

        Assert.assertEquals(10, phoneDirectoryService.getAllEntries().size());

        String name = "Shailendra";
        ContactDTO toUpdate = entries.get(0);
        toUpdate.setName(name);
        phoneDirectoryService.addEntry(toUpdate);
        Assert.assertEquals(10, phoneDirectoryService.getAllEntries().size());

        Assert.assertEquals(toUpdate, phoneDirectoryService.getEntry(toUpdate.getNumber()));
    }

    @Test(expected = Exception.class)
    public void throwExceptionIfNull(){
        ContactDTO entry = new ContactDTO();

        phoneDirectoryService.addEntry(entry);
    }
}
