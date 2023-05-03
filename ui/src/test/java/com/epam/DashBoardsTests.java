package com.epam;

import com.epam.pages.DashBoardsPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Named.named;

@Slf4j
@DisplayName("Dash Board Tests")
public class DashBoardsTests {

    private final DashBoardsPage dashBoards = new DashBoardsPage();
    private final WebDriverManager session = new WebDriverManager();

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
      String name = randomNumeric(6);
      dashBoards
              .clickAddNewDashBoardButton()
              .inputDashBoardName(name)
              .clickAddButtonModal()
              .checkDashBoardPresent(name);
  }
    
  @ParameterizedTest()
  @MethodSource("invalidDashNames")
  public void createDashInvalidName(String name){
        // TODO Dashboard with 130 chars long name will be created
      dashBoards
              .clickAddNewDashBoardButton()
              .inputDashBoardName(name)
              .clickAddButtonModal()
              .checkErrorPresent();
  }

    private static Stream<Arguments> invalidDashNames() {
        return Stream.of(
                Arguments.of(named("Name less than 3 chars", randomNumeric(2))),
                Arguments.of(named("Name more than 128 chars", randomNumeric(130))));
    }

  @Test
  @DisplayName("Create dashboard widget Test")
  public void deleteDashTest() {
        dashBoards.waitWhileReady();
        dashBoards
                .selectFirstDashBoard()
                .clickAddWidgetButton()
                .selectWidgetType(0)
                .clickNextStepButton()
                .selectWidgetDemoFilter()
                .clickNextStepButton()
                .saveWidget();
    }
}
