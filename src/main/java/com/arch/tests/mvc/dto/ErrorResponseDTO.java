package com.arch.tests.mvc.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ErrorResponseDTO {

    private int status;
    private String error;
    private String message;
    private List<String> details;
    private String path;
    private LocalDateTime timestamp;
}
