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
@Table(name="categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", length=250, nullable=false)
    @NotNull(message="Ten danh muc khong duoc de trong")
    private String name;

    @Column(name="create_time", nullable=false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name="update_time", nullable=false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @OneToMany(mappedBy="category", cascade=CascadeType.ALL, orphanRemoval=false)
    private List<Product> products = new ArrayList<>();

    public Category(String name){
        this.name =  name;
    }

    public List<Product> getProducts(){
        return products;
    }
}
