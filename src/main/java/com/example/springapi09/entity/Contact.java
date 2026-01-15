package com.example.springapi09.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="full_name", length=250, nullable=false)
    @NotNull(message="Ho va ten khong duoc de trong")
    private String fullName;

    @Column(name="phone_number", length=250, nullable=false)
    @NotNull(message="So dien thoai khong duoc de trong")
    private String phoneNumber;

    @Column(name="address", length=250, nullable=false)
    @NotNull(message="Dia chi khong duoc de trong")
    private String address;

    @Column(name="email", length=250, nullable=false)
    @NotNull(message="Email khong duoc de trong")
    @Email(message="Kiem tra lai dinh dang email")
    private String email;

    @Column(name="message", columnDefinition="Text", nullable=false)
    @NotNull(message="Message khong duoc de trong")
    private String message;

    public Contact(String fullName, String phoneNumber, String address, String email, String message){
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.message = message;
    }
}
