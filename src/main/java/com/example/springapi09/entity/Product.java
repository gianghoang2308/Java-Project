package com.example.springapi09.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 250, nullable = false)
    @NotNull(message = "Ten san pham khong de trong")
    private String name;

    @Column(name = "main_image")
    @NotNull(message = "Anh san pham khong de trong")
    private String mainImage;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Gia san pham khong de trong")
    private Double price;

    @Column(name = "sale_price", nullable = false)
    @NotNull(message = "Gia khuyen mai khong duoc de trong")
    private Double salePrice;

    @Column(name = "description", nullable = false, columnDefinition = "Text")
    @NotNull(message = "Mo ta san pham khong duoc de trong")
    private String description;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category_id_prd"), nullable = false)
    @JsonIgnore
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "fk_brand_id_prd"), nullable = false)
    @JsonIgnore
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_supplier_id_prd"), nullable = false)
    @JsonIgnore
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "assembleIn_id", foreignKey = @ForeignKey(name = "fk_assembleIn_id_prd"), nullable = false)
    private AssembleIn assembleIn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "color_id", foreignKey = @ForeignKey(name = "fk_color_id_prd"), nullable = false)
    private Color color;


    public Product(String name, String mainImage, Double price, String description, Boolean status, Category category, Brand brand, Supplier supplier, AssembleIn assembleIn, Color color) {
        this.name = name;
        this.mainImage = mainImage;
        this.price = price;
        this.salePrice = price * 0.9;
        this.description = description;
        this.status = status;
        this.category = category;
        this.brand = brand;
        this.supplier = supplier;
        this.assembleIn = assembleIn;
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setPrice(double price) {
        this.price = price;
        this.salePrice = price * 0.9;  // Recalculate sale price when price changes
    }

}
