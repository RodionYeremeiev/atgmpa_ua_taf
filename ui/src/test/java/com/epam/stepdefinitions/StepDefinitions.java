package com.epam.stepdefinitions;

import com.epam.WebDriverManager;
import com.epam.pages.DashBoardsPage;
import com.epam.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StepDefinitions {
    private final LoginPage login = new LoginPage();
    private final DashBoardsPage dashBoardsPage = new DashBoardsPage();
    private final WebDriverManager driverManager = new WebDriverManager();

    private static final String name = randomNumeric(6);

    @Given("open Report Portal page")
    public void openReportPortalPage() {
        driverManager.openReportPortal();
    }

    @When("user provide valid credentials")
    public void userProvideValidCredentials() {
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        log.info("Opening Report Portal Login Page");
        login.enterUserName(username).enterPassword(password).clickLoginButton();
    }

    @Then("Login successful")
    public void loginSuccessful() {
        dashBoardsPage.waitWhileReady();
    }

    @When("user click add new dashboard button")
    public void userClickAddNewDashboardButton() {
        dashBoardsPage.clickAddNewDashBoardButton();
    }

    @And("user input dashBoardName")
    public void userInputDashBoardName() {
        dashBoardsPage.inputDashBoardName(name);
    }

    @And("user click add dashboard modal button")
    public void userClickAddDashboardModalButton() {
          dashBoardsPage.clickAddButtonModal();
    }

    @Then("new dash board added to dashboards page")
    public void newDashBoardAddedToDashboardsPage() {
        dashBoardsPage.checkDashBoardPresent(name);
    }

    @When("user select first dashboard on dashboards page")
    public void userSelectFirstDashboardOnDashboardsPage() {
        dashBoardsPage.waitWhileReady();
        dashBoardsPage.selectFirstDashBoard();

    }

    @And("user click add widget button")
    public void userClickAddWidgetButton() {
         dashBoardsPage.clickAddWidgetButton();

    }

    @And("user select widget type")
    public void userSelectWidgetType() {
        dashBoardsPage.selectWidgetType(0);
    }

    @And("user click next step button")
    public void userClickNextStepButton() {
        dashBoardsPage.clickNextStepButton();
    }

    @And("user select demo filter widget")
    public void userSelectDemoFilterWidget() {
         dashBoardsPage.selectWidgetDemoFilter();
    }

    @And("user click save widget button")
    public void userClickSaveWidgetButton() {
        dashBoardsPage.saveWidget();
    }

    @Then("user widget added message appears")
    public void userWidgetAddedMessageAppears() {
        assertThat(dashBoardsPage.widgetAddedMessageDisplayed()).isTrue();
    }

    @Then("Error message is present")
    public void errorMessageIsPresent() {
        dashBoardsPage.checkErrorPresent();
    }

    @But("user provide a dashboard name with invalid length {int}")
    public void userProvideADashboardNameWithInvalidLength(int length) {
        String name = randomNumeric(length);
        dashBoardsPage.inputDashBoardName(name);
    }
}
