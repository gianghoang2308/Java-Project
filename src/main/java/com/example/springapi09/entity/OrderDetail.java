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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="quantity", nullable=false)
    private Integer quantity;

    @Column(name="unit_price", nullable=false)
    private Double unitPrice;

    @Column(name="total_price", nullable=false)
    private Double totalPrice;

    @Column(name="create_time", nullable=false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name="update_time", nullable=false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="order_id", foreignKey=@ForeignKey(name="fk_order_id_orderdetail"), nullable=false)
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="product_id", foreignKey=@ForeignKey(name="fk_product_id_orderdetail"), nullable=false)
    @JsonIgnore
    private Product product;

    public OrderDetail(Integer quantity,Double unitPrice, Double totalPrice, Order order, Product product){
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.order = order;
        this.product = product;
    }
}
