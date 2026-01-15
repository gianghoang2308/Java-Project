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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="full_name", length=250, nullable=false)
    @NotNull(message="Ho va ten khong duoc de trong")
    private String fullName;

    @Column(name="email", length=250, nullable=false)
    @NotNull(message="Email khong duoc de trong")
    @Email(message="Nhap dung dinh dang email")
    private String email;
    
    @Column(name="comment", columnDefinition="Text", nullable=false)
    @NotNull(message="Phan binh luan khong duoc de trong")
    private String comment;

    @Column(name="create_time", nullable=false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(name="update_time", nullable=false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Column(name="rate")
    private Integer rate;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_id_cmt"), nullable = false)
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="user_id", foreignKey = @ForeignKey(name="fk_user_id_cmt"), nullable=false)
    @JsonIgnore
    private User user;

    public Comment(String fullName, String email, String comment, Integer rate, Product product, User user){
        this.fullName = fullName;
        this.email = email;
        this.comment = comment;
        this.rate = rate;
        this.product = product;
        this.user = user;
    }
}
