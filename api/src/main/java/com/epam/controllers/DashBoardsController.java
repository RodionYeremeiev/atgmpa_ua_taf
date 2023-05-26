package com.epam.controllers;

import com.epam.models.CreateDashBoardBody;
import com.epam.models.dashUpdate.UpdateDashBoardBody;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static com.epam.config.ApiConfig.baseUrl;
import static com.epam.config.ApiConfig.dashBoardEndPoint;
import static com.epam.config.ApiConfig.token;

public class DashBoardsController {

    public static final String DASHBOARD_ENDPOINT = dashBoardEndPoint.concat("/%s");
    private final ApiController api = new ApiController();

    public Response getDashboards() {
        return api.getRequest(baseUrl, dashBoardEndPoint, token);
    }

    public Response getDashBoardById(int dashBoardId) {
        String endPoint = String.format(DASHBOARD_ENDPOINT, dashBoardId);
        return api.getRequest(baseUrl, endPoint, token);
    }

    public Response createDashBoard(String description, String name, boolean isShared) {
        CreateDashBoardBody body = CreateDashBoardBody.builder()
                .description(description)
                .name(name)
                .share(isShared)
                .build();
        return api.postRequest(baseUrl, dashBoardEndPoint, token, body);
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
        return api.deleteRequest(baseUrl, endPoint, token);
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
        return api.putRequest(baseUrl, endPoint, token, body);
    }
}
