package com.epam.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessageResponse {
    private int errorCode;
    private String message;
}
