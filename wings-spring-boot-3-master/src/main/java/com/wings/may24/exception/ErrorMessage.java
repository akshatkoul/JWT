package com.wings.may24.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private Instant timeStamp;
    private String errorMessage;
    private int status;
}
