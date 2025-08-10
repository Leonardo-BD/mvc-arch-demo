package com.arch.tests.mvc.controller;

import com.arch.tests.mvc.dto.UserDTO;
import com.arch.tests.mvc.flux.CreateUserFlux;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private CreateUserFlux createUserFlux;

    @Test
    void createUserShouldSuccessfullyExecuteTest() {
        doNothing().when(createUserFlux).execute(any(UserDTO.class));

        var response = userController.createUser(UserDTO.builder().build());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}