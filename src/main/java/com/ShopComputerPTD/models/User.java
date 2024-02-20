package com.ShopComputerPTD.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User extends BaseEntity implements UserDetails {

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> listAuthority= new ArrayList<>();
        listAuthority.add(new SimpleGrantedAuthority("ROLE_"+this.getRole().getName()));
        return listAuthority;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
