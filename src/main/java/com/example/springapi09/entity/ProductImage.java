package com.example.springapi09.entity;

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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="images", nullable=false, columnDefinition="Text")
    @NotNull(message="Anh san pham khong duoc de trong")
    private String images;

    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="product_id", foreignKey=@ForeignKey(name="fk_product_id_prdimgs"), nullable=false)
    @JsonIgnore
    private Product product;

    public ProductImage(String images, Product product){
        this.images = images;
        this.product = product;
    }
}
