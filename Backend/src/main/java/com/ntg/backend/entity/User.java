package com.ntg.backend.entity;

import com.ntg.backend.validation.ValidEgyptianNationalId;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED  )
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "First name is required")
    @Column(nullable = false, length = 10)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false, length = 10)
    private String lastName;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(nullable = false)
    private String password;

    private String role;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email must be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^(\\+20\\d{10}|01\\d{9})$",
            message = "Phone number must be valid (11 digits without country code or 12 digits with country code (+20))")
    @Column(nullable = false, unique = true)
    private String phone;

    @NotBlank(message = "National ID is required")
    @Size(min = 14, max = 14, message = "National ID must be exactly 14 digits")
    @ValidEgyptianNationalId
    @Column(nullable = false, unique = true, length = 14)
    private String nationalId;

    @NotBlank(message = "Gender is required")
    @Column(nullable = false, length = 10)
    private String gender;

    @Past(message = "Birth date must be in the past")
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    private String location;

    public void setPhone(String phone) {
        if (phone != null && !phone.startsWith("+20")) {
            this.phone = "+2" + phone;
        } else {
            this.phone = phone;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Create a list to hold the authorities
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        // Check the role and add the corresponding GrantedAuthority
//        if (role.equals("volunteer")) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_VOLUNTEER"));
//        } else if (role.equals("needy")) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_NEEDY"));
//        }
//
//        // Return an unmodifiable collection of authorities
//        return Collections.unmodifiableCollection(authorities);

        return Collections.singletonList(new SimpleGrantedAuthority(getRole()));
    }


    @Override
    public String getUsername() {
        return getEmail();
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