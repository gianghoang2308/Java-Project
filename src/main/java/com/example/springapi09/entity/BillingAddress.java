package com.example.springapi09.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "billing_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "billingaddress_firstname", length = 250, nullable = false)
    @NotNull(message = "Khong de trong first_name")
    private String billingAddressFirstName;

    @Column(name = "billingaddress_lastname", length = 250, nullable = false)
    @NotNull(message = "Khong de trong last_name")
    private String billingAddressLastName;

    @Column(name = "billingaddress_email", length = 250, nullable = false)
    @NotNull(message = "Khong de trong email")
    @Email(message = "Kiem tra lai, phai nhap dung dinh dang email")
    private String billingAddressEmail;

    @Column(name = "billingaddress_address", columnDefinition = "Text", nullable = false)
    @NotNull(message = "Khong de trong dia chi")
    private String billingAddressAddress;

    @Column(name = "billingaddress_city", length = 250, nullable = false)
    @NotNull(message = "Khong de trong ten thanh pho")
    private String billingAddressCity;

    @Column(name = "billingaddress_country", length = 250, nullable = false)
    @NotNull(message = "Khong de trong ten quoc gia")
    private String billingAddressCountry;

    @Column(name = "billingaddress_zipcode", length = 100, nullable = false)
    @NotNull(message = "Khong de trong ma buu dien")
    private String billingAddressZipCode;

    @Column(name = "billingaddress_telephone", length = 200, nullable = false)
    @NotNull(message = "So dien thoai khong de trong")
    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String billingAddressTelephone;

    @Column(name = "shippingto_difference_address", nullable = false)
    private Boolean shiptoDifferentAddress;

    @Column(name = "shippingaddress_firstname", length = 250, nullable = false)
    @NotNull(message = "khong de trong fist_name")
    private String shippingAddressFirstName;

    @Column(name = "shippingaddress_lastname", length = 250, nullable = false)
    @NotNull(message = "khong de trong last_name")
    private String shippingAddressLastName;

    @Column(name = "shippingaddress_email", length = 250, nullable = false)
    @NotNull(message = "Khong de trong email")
    @Email(message = "Kiem tra lai dinh dang email")
    private String shippingAddressEmail;

    @Column(name = "shippingadderss_address", columnDefinition = "Text", nullable = false)
    @NotNull(message = "Khong de trong dia chi")
    private String shippingAddressAddress;

    @Column(name = "shippingaddress_city", length = 250, nullable = false)
    @NotNull(message = "Khong de trong ten thanh pho")
    private String shippingAddressCity;

    @Column(name = "shippingaddress_country", length = 250, nullable = false)
    @NotNull(message = "Khong de trong ten quoc gia")
    private String shippingAddressCountry;

    @Column(name = "shippingaddress_zipcode", length = 100, nullable = false)
    @NotNull(message = "Khong de trong ma buu dien")
    private String shippingAddressZipCode;

    @Column(name = "shippingaddress_telephone", length = 200, nullable = false)
    @NotNull(message = "So dien thoai khong de trong")
    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String shippingAddressTelephone;
    //text

    @Column(name = "order_note", columnDefinition = "Text", nullable = false)
    @NotNull(message = "Khong de trong phan ghi chu")
    private String orderNote;

    public BillingAddress(
            String billingAddressFirstName,
            String billingAddressLastName,
            String billingAddressEmail,
            String billingAddressAddress,
            String billingAddressCity,
            String billingAddressCountry,
            String billingAddressZipCode,
            String billingAddressTelephone,
            Boolean shiptoDifferentAddress,
            String shippingAddressFirstName,
            String shippingAddressLastName,
            String shippingAddressEmail,
            String shippingAddressAddress,
            String shippingAddressCity,
            String shippingAddressCountry,
            String shippingAddressZipCode,
            String shippingAddressTelephone,
            String orderNote
    ) {
        this.billingAddressFirstName = billingAddressFirstName;
        this.billingAddressLastName = billingAddressLastName;
        this.billingAddressEmail = billingAddressEmail;
        this.billingAddressAddress = billingAddressAddress;
        this.billingAddressCity = billingAddressCity;
        this.billingAddressCountry = billingAddressCountry;
        this.billingAddressZipCode = billingAddressZipCode;
        this.billingAddressTelephone = billingAddressTelephone;
        this.shiptoDifferentAddress = shiptoDifferentAddress;
        this.shippingAddressFirstName = shippingAddressFirstName;
        this.shippingAddressLastName = shippingAddressLastName;
        this.shippingAddressEmail = shippingAddressEmail;
        this.shippingAddressAddress = shippingAddressAddress;
        this.shippingAddressCity = shippingAddressCity;
        this.shippingAddressCountry = shippingAddressCountry;
        this.shippingAddressZipCode = shippingAddressZipCode;
        this.shippingAddressTelephone = shippingAddressTelephone;
        this.orderNote = orderNote;
    }

}
