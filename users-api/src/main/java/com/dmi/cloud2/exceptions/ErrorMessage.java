package com.dmi.cloud2.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private final LocalDateTime dateTime;
    private final String message;
}
