package com.epam;

import com.epam.controllers.ApiController;
import com.epam.controllers.DashBoardsController;
import com.epam.models.ErrorMessage;
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
    public static final String DASHBOARD_WITH_INVALID_ID_MESSAGE = "Dashboard with ID '%s' not found on project 'testuseratgmpa_personal'. Did you use correct Dashboard ID?";
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
        api.checkStatusCode(dashBoardsController.getDashBoardById("104606"), 200);
    }

    @Test
    @DisplayName("Get DashBoard By Invalid ID Test")
    public void getInvalidDashBoardTest() {
        String invalidId = "565656";
        Response response = dashBoardsController.getDashBoardById(invalidId);
        ErrorMessage errorMessage = response.as(ErrorMessage.class);
        assertThat(errorMessage.getErrorCode())
                .isEqualTo(40422);
        assertThat(errorMessage.getMessage())
                .isEqualTo(String.format(DASHBOARD_WITH_INVALID_ID_MESSAGE, invalidId));
        api.checkStatusCode(response, 404);
    }

    @Test
    @DisplayName("Create DashBoard with Valid name Test")
    public void createDashBoardValidNameTest() {
        api.checkStatusCode(dashBoardsController.createDashBoard(), 201);
    }

    @ParameterizedTest()
    @MethodSource("invalidDashNames")
    public void createDashBoardLongNameTest(String name) {
        Response response = dashBoardsController.createDashBoard(name);
        ErrorMessage errorMessage = response.as(ErrorMessage.class);
        assertThat(errorMessage.getErrorCode())
                .isEqualTo(4001);
        assertThat(errorMessage.getMessage())
                .isEqualTo(INCORRECT_NAME_SIZE_MESSAGE);
        api.checkStatusCode(response, 400);
    }

    private static Stream<Arguments> invalidDashNames() {
        return Stream.of(
                Arguments.of(Named.named("Create DashBoard with 130 symbols name Test", RandomStringUtils.random(130))),
                Arguments.of(Named.named("Create DashBoard with 1 symbol name Test", RandomStringUtils.random(1)))
        );
    }
}
