package com.ShopComputerPTD.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="email",length = 100)
    private String email;

    @Column(name="phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name="address", length = 200)
    private String address;

    private String note;

    @Column(name="order_date")
    private LocalDateTime orderDate;

    @Column(name="status")
    private String status;

    @Column(name="total_money")
    private Float totalMoney;

    @Column(name="shipping_method",length = 100)
    private String shippingMethod;

    @Column(name="shipping_address", length = 200, nullable = false)
    private String shippingAddress;

    @Column(name="shipping_date")
    private Date shippingDate;

    @Column(name="tracking_number")
    private String trackingNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name="active")
    private Boolean active;

}
