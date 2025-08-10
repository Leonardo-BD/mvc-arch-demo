package com.arch.tests.mvc.validator;

import com.arch.tests.mvc.exception.ApplicationException;
import com.arch.tests.mvc.exception.ExceptionEnum;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public UserValidator validateEmailLength(String email) {
        if (email == null || email.isBlank()) {
            throw new ApplicationException(ExceptionEnum.EMPTY_EMAIL_PARAMETER);
        }
        return this;
    }
}
