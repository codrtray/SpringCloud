package com.dmi.cloud2.controllers;

import com.dmi.cloud2.model.CreateUserRequestModel;
import com.dmi.cloud2.model.CreateUserResponseModel;
import com.dmi.cloud2.model.UserDto;
import com.dmi.cloud2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Environment environment;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, Environment environment, ModelMapper modelMapper) {
        this.userService = userService;
        this.environment = environment;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on " + environment.getProperty("local.server.port");
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel createUserRequestModel) {
        UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        CreateUserResponseModel createUserResponseModel = modelMapper.map(createdUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponseModel);
    }
}
