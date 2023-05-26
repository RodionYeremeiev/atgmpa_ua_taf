package com.epam.models.dashUpdate;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateDashBoardBody {
    private String description;
    private String name;
    private boolean share;
    List<UpdateWidget>updateWidgets;
}
