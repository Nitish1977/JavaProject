package com.contactbook.service;

import com.contactbook.model.Contact;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ContactService {
    private final Map<Long, Contact> contacts = new LinkedHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    public Contact getContact(Long id) {
        return contacts.get(id);
    }

    public void addContact(Contact contact) {
        contact.setId(idCounter.getAndIncrement());
        contacts.put(contact.getId(), contact);
    }

    public void updateContact(Contact contact) {
        contacts.put(contact.getId(), contact);
    }

    public void deleteContact(Long id) {
        contacts.remove(id);
    }

    public List<Contact> searchContacts(String name) {
        List<Contact> result = new ArrayList<>();
        for (Contact c : contacts.values()) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }
}
