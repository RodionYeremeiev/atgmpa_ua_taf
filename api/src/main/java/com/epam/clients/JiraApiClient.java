package com.epam.clients;


import com.epam.config.ApiConfig;
import com.epam.models.ZephyrTestExecBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JiraApiClient {

    private final RestApiClient restApiClient = new RestApiClient();

    public void updateTestExecutionStatus(String testCaseId, String status) {
        ZephyrTestExecBody body = ZephyrTestExecBody.testExecBody(testCaseId, status);
        log.info(String.format("Updating execution Status for %s to %s", testCaseId, status));
        restApiClient.postRequest(
                ApiConfig.ZEPHYR_API_BASE_URL,
                "testexecutions",
                System.getProperty("zephyrApiToken"),
                body)
                .prettyPrint();
    }
}
