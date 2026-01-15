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
@Table(name="colors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="color_code", length=200, nullable=false)
    @NotNull(message="Ma mau khong duoc de trong")
    private String colorCode;

    @Column(name="name", length=200, nullable=false)
    @NotNull(message="Ten mau khong duoc de trong")
    private String name;

    @Column(name="create_time", nullable=false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name="update_time", nullable=false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @OneToMany(mappedBy="color", cascade=CascadeType.ALL, orphanRemoval=false)
    private List<Product> products = new ArrayList<>();

    public Color(String coreCode, String name){
        this.colorCode = coreCode;
        this.name = name;
    }
}
