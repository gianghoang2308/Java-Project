package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.Contact.ContactUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Contact.ContactRequest;
import com.example.springapi09.dto.Contact.ContactResponse;
import com.example.springapi09.entity.Contact;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.ContactMapper;
import com.example.springapi09.repository.ContactRepository;
import com.example.springapi09.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactMapper contactMapper;

    @Override
    public List<ContactResponse> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();

        if (contacts.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }

        return contactMapper.toResponseList(contacts);
    }

    @Override
    public ContactResponse findById(Integer id) {
        Optional<Contact> contact = contactRepository.findById(Objects.requireNonNull(id));
        if (contact.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }

        return contactMapper.toResponse(contact.get());
    }

    @Override
    public String create(ContactRequest contactRequest) {
        if (contactRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }
        Contact create = contactMapper.toContact(contactRequest);
        contactRepository.save(Objects.requireNonNull(create));
        return "Successfully create new contact!";
    }

    @Override
    public String update(ContactUpdate contactUpdate, Integer id) {
        if (contactUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find category with id: " + id));

        if (contactUpdate.getFullName() != null && !contactUpdate.getFullName().isEmpty()) {
            existing.setFullName(contactUpdate.getFullName());
        }

        if (contactUpdate.getPhoneNumber() != null && !contactUpdate.getPhoneNumber().isEmpty()) {
            existing.setPhoneNumber(contactUpdate.getPhoneNumber());
        }

        if (contactUpdate.getAddress() != null && !contactUpdate.getAddress().isEmpty()) {
            existing.setAddress(contactUpdate.getAddress());
        }

        if (contactUpdate.getEmail() != null && !contactUpdate.getEmail().isEmpty()) {
            existing.setEmail(contactUpdate.getEmail());
        }

        if (contactUpdate.getMessage() != null && !contactUpdate.getMessage().isEmpty()) {
            existing.setMessage(contactUpdate.getMessage());
        }

        contactRepository.save(existing);
        return "Successfully updated category with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<Contact> contact = contactRepository.findById(Objects.requireNonNull(id));
        if (contact.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        contactRepository.deleteById(id);
        return "Successfully delete contact with id: " + id;
    }
}
