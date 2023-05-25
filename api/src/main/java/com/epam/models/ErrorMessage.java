package com.epam.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private int errorCode;
    private String message;
}
