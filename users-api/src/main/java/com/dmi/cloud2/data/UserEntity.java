package com.dmi.cloud2.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -5961570947841950503L;

    private long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false)
    private String encryptedPassword;


    private Set<Address> addresses = new HashSet<>();

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    @OneToMany(mappedBy = "user")
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
        address.setUser(this);
    }
}
