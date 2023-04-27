package com.epam;

import com.epam.pages.DashBoardsPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@DisplayName("Dash Board Tests")
public class DashBoardsTests {

    private final DashBoardsPage dashBoards = new DashBoardsPage();
    private final SessionManager session = new SessionManager();

    @BeforeEach
    public void setup() {
        session.setup();
    }

    @AfterEach
    public void tearDown() {
        session.tearDown();
    }

    @Test
    @DisplayName("Add dash new dashboard Test")
    @Tag("addDash")
    public void createDashTest() {
        dashBoards.addNewDashBoard();
    }

    @Test
    @DisplayName("Create dashboard widget Test")
    public void deleteDashTest() {
        dashBoards
                .waitWhileReady()
                .selectFirstDashBoard()
                .clickAddWidgetButton()
                .selectWidgetType(0)
                .clickNextStepButton()
                .selectWidgetDemoFilter()
                .clickNextStepButton()
                .saveWidget();
    }
}
