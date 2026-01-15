package com.example.springapi09.dto.Supplier;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierResponse {

    private String name;

    private String address;

    private String email;

    private String phoneNumber;

    private String taxCode;

    private String website;

    private String logo;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
