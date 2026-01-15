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
@Table(name = "configs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email_1", length = 250, nullable = false)
    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email1;

    @Column(name = "email_2", length = 250, nullable = false)
    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email2;

    @Column(name = "email_3", length = 250, nullable = false)
    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email3;

    @Column(name = "email_4", length = 250, nullable = false)
    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email4;

    @Column(name = "social_link_1", length = 250, nullable = false)
    @NotNull(message = "Social link khong duoc de trong")
    private String socialLink1;

    @Column(name = "social_link_2", length = 250, nullable = false)
    @NotNull(message = "Social link khong duoc de trong")
    private String socialLink2;

    @Column(name = "social_link_3", length = 250, nullable = false)
    @NotNull(message = "Social link khong duoc de trong")
    private String socialLink3;

    @Column(name = "social_link_4", length = 250, nullable = false)
    @NotNull(message = "Social link khong duoc de trong")
    private String socialLink4;

    @Column(name = "contact", columnDefinition = "Text", nullable = false)
    @NotNull(message = "Thong tin lien he khong duoc de trong")
    private String contact;

    @Column(name = "title", length = 250, nullable = false)
    @NotNull(message = "Tieu de khong duoc de trong")
    private String title;

    @Column(name = "description", length = 250, nullable = false)
    @NotNull(message = "Mo ta khong duoc de trong")
    private String description;

    @Column(name = "hot_line", length = 250, nullable = false)
    @NotNull(message = "So hotline lien lac khong duoc de trong")
    private String hotLine;

    @Column(name = "address", length = 250, nullable = false)
    @NotNull(message = "Dia chi khong duoc de trong")
    private String address;

    public Config(String email1, String email2, String email3, String email4,
            String socialLink1, String socialLink2, String socialLink3, String socialLink4,
            String contact, String description, String hotLine, String address, String title) {
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.email4 = email4;
        this.socialLink1 = socialLink1;
        this.socialLink2 = socialLink2;
        this.socialLink3 = socialLink3;
        this.socialLink4 = socialLink4;
        this.contact = contact;
        this.description = description;
        this.hotLine = hotLine;
        this.address = address;
        this.title = title;
    }
}
