package com.example.springapi09.entity;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_time", nullable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    /*
    1. Cash
    2. CreaditCard
    3. BankTransfer
    4. QR Code
     */
    @Column(name="payment_method", nullable=false)
    private Integer paymentMethod = 1;

    @Column(name="total_amount", nullable=false)
    private Integer totalAmount;

    @Column(name="shipping_fee", nullable=false)
    private Double shippingFee;

    @Column(name="is_term_accepted", nullable=false)
    private Boolean isTermAccepted;

    @Column(name="create_time", nullable=false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name="update_time", nullable=false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="user_id", foreignKey=@ForeignKey(name="fk_user_id_order"), nullable=false)
    @JsonIgnore
    private User user;

    public Order(LocalDateTime orderTime ,Integer paymentMethod ,Integer totalAmount ,
        Double shippingFee, Boolean isTermAccepted, User user){
            this.orderTime = orderTime;
            this.paymentMethod = paymentMethod;
            this.totalAmount = totalAmount;
            this.shippingFee = shippingFee;
            this.isTermAccepted = isTermAccepted;
            this.user = user;
    }
}
