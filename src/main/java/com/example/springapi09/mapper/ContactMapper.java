package com.example.springapi09.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Contact.ContactRequest;
import com.example.springapi09.dto.Contact.ContactResponse;
import com.example.springapi09.entity.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    Contact toContact(ContactRequest contactRequest);

    ContactResponse toResponse(Contact contact);

    List<ContactResponse> toResponseList(List<Contact> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ContactRequest contactRequest, @MappingTarget Contact contact);
}
