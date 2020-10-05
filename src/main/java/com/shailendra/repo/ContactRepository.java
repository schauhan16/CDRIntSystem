package com.shailendra.repo;

import com.shailendra.pojo.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findByNumber(long number);

    List<Contact> findAll();

    void deleteByNumber(long number);
}
