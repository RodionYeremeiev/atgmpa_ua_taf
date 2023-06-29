package com.epam.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZephyrTestExecBody {
    private String projectKey;
    private String testCaseKey;
    private String testCycleKey;
    private String statusName;

    public static ZephyrTestExecBody testExecBody(String testCaseKey, String statusName) {
        return ZephyrTestExecBody.builder()
                .projectKey("AUT")
                .testCaseKey(testCaseKey)
                .testCycleKey("AUT-R2")
                .statusName(statusName)
                .build();
    }
}
