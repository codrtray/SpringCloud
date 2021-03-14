package com.dmi.cloud2.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequestModel {
    @NotBlank(message = "provide first name")
    private final String firstName;
    @NotBlank(message = "provide last name")
    private final String lastName;
    @Size(min = 8, message = "password must be more 8 long")
    @NotBlank(message = "password cannot be empty")
    private final String password;
    @Email(message = "It isn't valid email")
    private final String email;
}
