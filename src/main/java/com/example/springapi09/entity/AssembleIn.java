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
@Table(name="assembleins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssembleIn {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="country", length=250, nullable=false, unique=true)
    @NotNull(message="Ten quoc gia khong de trong!")
    private String country;

    @Column(name="create_time", nullable=false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name="update_time", nullable=false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @OneToMany(mappedBy="assembleIn", cascade=CascadeType.ALL, orphanRemoval=false)
    private List<Product> products = new ArrayList<>();

    public AssembleIn(String country){
        this.country = country;
    }

    public List<Product> getProducts(){
        return products;
    }
}
