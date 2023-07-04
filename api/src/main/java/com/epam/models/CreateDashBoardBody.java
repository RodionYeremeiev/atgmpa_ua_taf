package com.epam.models;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDashBoardBody {
    private String description;
    private String name;
    private boolean share;
}
