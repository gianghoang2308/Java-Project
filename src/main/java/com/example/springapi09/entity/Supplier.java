package com.example.springapi09.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 250, nullable = false)
    @NotNull(message = "Ten nha cung cap khong duoc de trong")
    private String name;

    @Column(name = "address", columnDefinition = "Text", nullable = false)
    @NotNull(message = "Dia chi nha cung cap khong duoc de trong")
    private String address;

    @Column(name = "email", length = 250, nullable = false)
    @NotNull(message = "Email nha cung cap khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")

    private String email;

    @Column(name = "phone_number", length = 250, nullable = false)
    @NotNull(message = "So dien thoai nha cung cao khong duoc de trong")
    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String phoneNumber;

    @Column(name = "tax_code", length = 250, nullable = false)
    @NotNull(message = "Ma so thue nha cung cap khong duoc de trong")
    private String taxCode;

    @Column(name = "website", length = 250, nullable = false)
    @NotNull(message = "Dia chi website cua nha cung cap khong duoc de trong")
    private String website;

    @Column(name = "logo", nullable = false)
    @NotNull(message = "Logo cua nha cung cap khong duoc de trong")
    private String logo;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Product> products = new ArrayList<>();

    public Supplier(
            String name,
            String address,
            String email,
            String phoneNumber,
            String taxCode,
            String website,
            String logo
    ) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.taxCode = taxCode;
        this.website = website;
        this.logo = logo;
    }

}
