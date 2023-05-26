package com.epam;

import com.epam.controllers.ApiController;
import com.epam.controllers.DashBoardsController;
import com.epam.models.DashCreateResponse;
import com.epam.models.ErrorMessageResponse;
import com.epam.models.MessageResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiTests {

    public static final String INCORRECT_NAME_SIZE_MESSAGE = "Incorrect Request. [Field 'name' should have size from '3' to '128'.] ";
    public static final String DASHBOARD_NOT_FOUND_MESSAGE = "Dashboard with ID '%s' not found on project 'testuseratgmpa_personal'. Did you use correct Dashboard ID?";
    public static final String DASHBOARD_DELETED_MESSAGE = "Dashboard with ID = '%s' successfully deleted.";
    public static final String DASHBOARD_UPDATED_MESSAGE = "Dashboard with ID = '%s' successfully updated";
    public static final int NOT_EXIST_ID = 565656;
    public static final int DEMO_DASH_ID = 104606;
    private final DashBoardsController dashBoardsController = new DashBoardsController();
    private final ApiController api = new ApiController();

    @Test
    @DisplayName("Get DashBoards Test")
    public void getDashBoardsTest() {
        api.checkStatusCode(dashBoardsController.getDashboards(), 200);
    }

    @Test
    @DisplayName("Get DashBoard By Valid ID Test")
    public void getValidDashBoardTest() {
        api.checkStatusCode(dashBoardsController.getDashBoardById(DEMO_DASH_ID), 200);
    }

    @Test
    @DisplayName("Get DashBoard By Invalid ID Test")
    public void getInvalidDashBoardTest() {
        Response response = dashBoardsController.getDashBoardById(NOT_EXIST_ID);
        verifyDashBoardNotFound(response);
    }

    @Test
    @DisplayName("Create DashBoard with Valid name Test")
    public void createDashBoardValidNameTest() {
        api.checkStatusCode(dashBoardsController.createDashBoard(), 201);
    }

    @ParameterizedTest()
    @MethodSource("invalidDashNames")
    public void createDashBoardInvalidNamesTest(String name) {
        Response response = dashBoardsController.createDashBoard(name);
        ErrorMessageResponse errorMessage = response.as(ErrorMessageResponse.class);
        assertThat(errorMessage.getErrorCode())
                .isEqualTo(4001);
        assertThat(errorMessage.getMessage())
                .isEqualTo(INCORRECT_NAME_SIZE_MESSAGE);
        api.checkStatusCode(response, 400);
    }

    @Test
    @DisplayName("Update DashBoard with Valid ID Test")
    public void updateValidDashBoard(){
        Response response = dashBoardsController.updateDashBoard(DEMO_DASH_ID);
        api.checkStatusCode(response, 200);
        String message = response.as(MessageResponse.class).getMessage();
        assertThat(message).isEqualTo(String.format(DASHBOARD_UPDATED_MESSAGE, DEMO_DASH_ID));
    }

    @Test
    @DisplayName("Update Not Existing DashBoard Test")
    public void updateNotExistingDashBoard(){
        Response response = dashBoardsController.updateDashBoard(NOT_EXIST_ID);
        api.checkStatusCode(response, 404);
        String message = response.as(MessageResponse.class).getMessage();
        assertThat(message)
                .isEqualTo(String.format(DASHBOARD_NOT_FOUND_MESSAGE, NOT_EXIST_ID));
        response.then().log().all();
    }

    @ParameterizedTest()
    @MethodSource("invalidDashNames")
    public void updateDashBoardInvalidNamesTest(String name) {
        Response response = dashBoardsController.updateDashBoard(
                DEMO_DASH_ID, name, "WILL NOT BE UPDATED");
        ErrorMessageResponse errorMessage = response.as(ErrorMessageResponse.class);
        assertThat(errorMessage.getErrorCode())
                .isEqualTo(4001);
        assertThat(errorMessage.getMessage())
                .isEqualTo(INCORRECT_NAME_SIZE_MESSAGE);
        api.checkStatusCode(response, 400);
    }

    @Test
    @DisplayName("Delete DashBoard with Valid ID Test")
    public void deleteDashBoardValidIdTest() {
        Response createResponse = dashBoardsController.createDashBoard();
        api.checkStatusCode(createResponse, 201);
        int id = createResponse.as(DashCreateResponse.class).getId();
        Response deleteResponse = dashBoardsController.deleteDashboard(id);
        api.checkStatusCode(deleteResponse, 200);
        String message = deleteResponse.as(MessageResponse.class).getMessage();
        assertThat(message)
                .as("Verify Dash Board deleted")
                .isEqualTo(String.format(DASHBOARD_DELETED_MESSAGE, id));
    }

    @Test
    @DisplayName("Delete DashBoard with Invalid ID Test")
    public void deleteDashBoardInvalidIdTest() {
        Response response = dashBoardsController.deleteDashboard(NOT_EXIST_ID);
        verifyDashBoardNotFound(response);
    }

    private void verifyDashBoardNotFound(Response response) {
        ErrorMessageResponse errorMessage = response.as(ErrorMessageResponse.class);
        assertThat(errorMessage.getErrorCode())
                .isEqualTo(40422);
        assertThat(errorMessage.getMessage())
                .isEqualTo(String.format(DASHBOARD_NOT_FOUND_MESSAGE, NOT_EXIST_ID));
        api.checkStatusCode(response, 404);
    }

    private static Stream<Arguments> invalidDashNames() {
        return Stream.of(
                Arguments.of(Named.named("Create DashBoard with 130 symbols name Test",
                        RandomStringUtils.random(130))),
                Arguments.of(Named.named("Create DashBoard with 1 symbol name Test",
                        RandomStringUtils.random(1)))
        );
    }
}
