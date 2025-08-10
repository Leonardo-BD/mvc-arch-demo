package com.arch.tests.mvc.controller;

import com.arch.tests.mvc.dto.UserDTO;
import com.arch.tests.mvc.flux.CreateUserFlux;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserFlux createUserFlux;
    //private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserDTO userDTO) {
        createUserFlux.execute(userDTO);
        //userService.createUser(User.builder().build());

        return ResponseEntity.ok().build();
    }
}
