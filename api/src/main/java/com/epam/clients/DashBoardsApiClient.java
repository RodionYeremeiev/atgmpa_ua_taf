package com.epam.clients;

import com.epam.models.CreateDashBoardBody;
import com.epam.models.dashUpdate.UpdateDashBoardBody;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static com.epam.config.ApiConfig.REPORT_PORTAL_BASE_URL;
import static com.epam.config.ApiConfig.DASH_BOARDS_END_POINT;
import static com.epam.config.ApiConfig.REPORT_PORTAL_TOKEN;

public class DashBoardsApiClient {

    private static final String DASHBOARD_ENDPOINT = DASH_BOARDS_END_POINT.concat("/%s");
    private final RestApiClient api = new RestApiClient();

    public Response getDashboards() {
        return api.getRequest(REPORT_PORTAL_BASE_URL, DASH_BOARDS_END_POINT, REPORT_PORTAL_TOKEN);
    }

    public Response getDashBoardById(int dashBoardId) {
        String endPoint = String.format(DASHBOARD_ENDPOINT, dashBoardId);
        return api.getRequest(REPORT_PORTAL_BASE_URL, endPoint, REPORT_PORTAL_TOKEN);
    }

    public Response createDashBoard(String description, String name, boolean isShared) {
        CreateDashBoardBody body = CreateDashBoardBody.builder()
                .description(description)
                .name(name)
                .share(isShared)
                .build();
        return api.postRequest(REPORT_PORTAL_BASE_URL, DASH_BOARDS_END_POINT, REPORT_PORTAL_TOKEN, body);
    }

    public Response createDashBoard() {
        return createDashBoard(
                "Dash created By Api",
                RandomStringUtils.randomNumeric(6),
                true);
    }

    public Response createDashBoard(String name) {
        return createDashBoard(
                "Dash created By Api",
                name,
                true);
    }

    public Response deleteDashboard(int id) {
        String endPoint = String.format(DASHBOARD_ENDPOINT, id);
        return api.deleteRequest(REPORT_PORTAL_BASE_URL, endPoint, REPORT_PORTAL_TOKEN);
    }

    public Response updateDashBoard(int id){
        return updateDashBoard(id, "DEMO DASHBOARD (Updated via API)", "DEMO DASHBOARD");
    }

    public Response updateDashBoard(int id, String name, String description){
        String endPoint = String.format(DASHBOARD_ENDPOINT, id);
        UpdateDashBoardBody body = UpdateDashBoardBody.builder()
                .description(description)
                .name(name)
                .share(true)
                .build();
        return api.putRequest(REPORT_PORTAL_BASE_URL, endPoint, REPORT_PORTAL_TOKEN, body);
    }
}
