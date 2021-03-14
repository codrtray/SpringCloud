package com.dmi.cloud2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Getter @Setter
public class UserDto implements Serializable {

    private static final long serialVersionUID = 3502543845260117672L;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String userId;
    private String encryptedPassword;
}
