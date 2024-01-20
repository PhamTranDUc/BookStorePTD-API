package com.BookStorePTD.BookStorePTD.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

//
@Entity
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name",length = 100)
    private String fullName;

    @Column(name = "phone_number",length = 11,nullable = false)
    private String phoneNumber;

    @Column(name="address",length = 200)
    private String address;

    @Column(name = "user_name",length = 30,nullable = false)
    private String userName;

    @Column(name = "pass_word", length = 100,nullable = false)
    private String password;

    @Column(name = "email",length = 30)
    private String email;

    @Column(name="is_active")
    private boolean active;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name= "facebook_account_id")
    private int facebookAccountId;

    @Column(name="google_account_id")
    private int googleAccountId;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
