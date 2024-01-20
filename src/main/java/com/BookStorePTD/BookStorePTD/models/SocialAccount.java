package com.BookStorePTD.BookStorePTD.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name="social_accounts")
@Builder
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="provider", nullable = false)
    private String provider;

    @Column(name= "provider_id",nullable = false)
    private String providerId;

    private String email;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
