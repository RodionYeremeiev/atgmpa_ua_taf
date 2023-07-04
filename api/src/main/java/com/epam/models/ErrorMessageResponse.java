package com.epam.models;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageResponse {
    private int errorCode;
    private String message;
}
