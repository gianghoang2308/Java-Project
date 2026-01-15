package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Contact.ContactRequest;
import com.example.springapi09.dto.Contact.ContactResponse;
import com.example.springapi09.dto.Contact.ContactUpdate;

public interface ContactService {
    List<ContactResponse> getAllContacts();
    ContactResponse findById(Integer id);
    String create(ContactRequest contactRequest);
    String update(ContactUpdate contactUpdate, Integer id);
    String delete(Integer id);

}
