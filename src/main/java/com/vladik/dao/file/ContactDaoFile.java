package com.vladik.dao.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladik.dao.ContactDao;
import com.vladik.model.Contact;
import com.vladik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ContactDaoFile implements ContactDao {
    final public String FILE_PATH = "src/main/resources/contacts.json";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserDaoFile userDaoFile;

    @Override
    public List<Contact> findByUserAndFirstNameContains(User user, String expression) {
        List<Contact> contacts = user.getContacts();
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts
                ) {
            if (contact.getFirstName().contains(expression)) {
                result.add(contact);
            }
        }
        return result;
    }

    @Override
    public List<Contact> searchByUserAndMobileNumber(String usersId, String mobileNumberExp) {
        List<Contact> contacts = userDaoFile.findOne(Integer.valueOf(usersId)).getContacts();
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts
                ) {
            if (contact.getMobileNumber().contains(mobileNumberExp)) {
                result.add(contact);
            }
        }
        return result;
    }

    @Override
    public List<Contact> searchByUserAndMobileNumberAndFirstName(String usersId, String mobileNumberExp, String firstNameExp) {
        List<Contact> contacts = userDaoFile.findOne(Integer.valueOf(usersId)).getContacts();
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts
                ) {
            if (contact.getMobileNumber().contains(mobileNumberExp)) {
                result.add(contact);
            }
        }
        for (Contact contact : result
                ) {
            if (!contact.getFirstName().contains(mobileNumberExp)) {
                result.remove(contact);
            }

        }
        return result;
    }

    @Override
    public <S extends Contact> S save(S s) {
        List<Contact> contacts = getContacts();
        boolean isUnique = true;
        for (Contact contact : contacts
                ) {
            if (contact.getId() == s.getId())
                isUnique = false;
        }
        if (isUnique) {
            s.setId((int) UUID.randomUUID().getLeastSignificantBits());

        } else {
            delete(s.getId());

        }
        try {
            contacts.add(s);
            objectMapper.writeValue(new File(FILE_PATH), contacts);
            userDaoFile.addContact(s, s.getUser());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }

    @Override
    public void delete(Integer id) {
        List<Contact> contacts = getContacts();
        Contact contact = findOne(id);
        contacts.remove(contacts.indexOf(contact));
        try {
            objectMapper.writeValue(new File(FILE_PATH), contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userDaoFile.deleteContact(id);
    }

    @Override
    public Contact findOne(Integer id) {
        List<Contact> contacts = getContacts();
        Contact contact = null;

        for (Contact element :
                contacts) {
            if (element.getId() == id) {
                contact = element;
            }
        }
        return contact;
    }

    public List<Contact> getContacts() {
        File file = new File(FILE_PATH);
        List<Contact> contacts = null;
        if (file.exists() && !file.isDirectory()) {
            try {
                contacts = objectMapper.readValue(file, new TypeReference<List<Contact>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            contacts = new ArrayList<>();
        }
        return contacts;

    }
}
