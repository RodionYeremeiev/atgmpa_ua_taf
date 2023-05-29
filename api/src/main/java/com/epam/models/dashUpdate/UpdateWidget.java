package com.epam.models.dashUpdate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateWidget {
    private boolean share;
    private int widgetId;
    private String widgetName;
    private String widgetType;
}
