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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", length=200, nullable=false)
    @NotNull(message="Ten thuong hieu khong duoc de trong")
    private String name;

    @Column(name="logo", nullable=false)
    @NotNull(message="Logo thuong hieu khong duoc de trong")
    private String logo;

    @Column(name="description", columnDefinition="Text" , nullable=false)
    @NotNull(message="Mo ta thuong hieu khong duoc de trong")
    private String description;

    @Column(name="create_time", nullable=false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name="update_time", nullable=false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @OneToMany(mappedBy="brand", cascade=CascadeType.ALL, orphanRemoval=false)
    public List<Product> products = new ArrayList<>();

    public Brand(String name, String logo, String description){
        this.name = name;
        this.logo = logo;
        this.description = description;
    }

    public List<Product> getProducts(){
        return products;
    }
}
