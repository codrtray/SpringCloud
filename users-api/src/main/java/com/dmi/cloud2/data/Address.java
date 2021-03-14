package com.dmi.cloud2.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter @Setter
public class Address {

    private Long id;
    private String city;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private UserEntity user;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }


}
