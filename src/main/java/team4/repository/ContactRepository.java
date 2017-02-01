package team4.repository;

import team4.domain.Contact;

import java.util.List;

/**
 * Created by liren
 */
public interface ContactRepository {

    List<Contact> findAll();

    void save(Contact contact);
}
