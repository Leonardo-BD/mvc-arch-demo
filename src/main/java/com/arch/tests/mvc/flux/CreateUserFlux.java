package com.arch.tests.mvc.flux;

import com.arch.tests.mvc.dto.UserDTO;
import com.arch.tests.mvc.entity.User;
import com.arch.tests.mvc.service.UserService;
import com.arch.tests.mvc.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserFlux {

    private final UserService userService;
    private final UserValidator userValidator;

    public void execute(UserDTO userDTO) {
        userValidator.validateEmailLength(userDTO.email());

        //TODO Implement MapStruct
        var user = User.builder()
                .name(userDTO.name())
                .email(userDTO.email())
                .build();

        userService.createUser(user);
    }
}
