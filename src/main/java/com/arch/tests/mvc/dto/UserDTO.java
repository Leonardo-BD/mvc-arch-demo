package com.arch.tests.mvc.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserDTO(
        @NotBlank(message = "Name must not be empty")
        @Size(min = 1, max = 255, message = "Name must have 1 to 255 characters")
        String name,
        @NotBlank(message = "E-mail must not be empty")
        @Size(min = 1, max = 255, message = "E-mail must have 1 to 255 characters")
        @Email(message = "E-mail must be valid")
        String email
) {}
