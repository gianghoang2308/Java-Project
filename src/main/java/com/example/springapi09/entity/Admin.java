package com.example.springapi09.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "admin_id", length = 250, nullable = false, unique = true)
    private String adminId;

    @Column(name = "account_name", length = 250, nullable = false, updatable = false, unique = true)
    private String name;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    @Column(name = "full_name", length = 250, nullable = false)
    private String fullName;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Column(name = "phone_number", length = 200, nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "idcard_front", nullable = false)
    private String idCardFront;

    @Column(name = "idcard_back", nullable = false)
    private String idCardBack;

    @Column(name = "date_of_issue", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    @Column(name = "place_of_issue", columnDefinition = "Text", nullable = false)
    private String placeOfIssue;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_id_adm"), nullable = false)
    @JsonIgnore
    private Role role;

    public Admin(String adminId, String name, String password, String fullname,
            String avatar, String phoneNumber, String email, String idCardFront,
            String idCardBack, LocalDate dateOfIssue, String placeOfIssue, Role role) {
        this.adminId = adminId;
        this.name = name;
        this.password = password;
        this.fullName = fullname;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCardFront = idCardFront;
        this.idCardBack = idCardBack;
        this.dateOfIssue = dateOfIssue;
        this.placeOfIssue = placeOfIssue;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.time.LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.time.LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public java.time.LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(java.time.LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
