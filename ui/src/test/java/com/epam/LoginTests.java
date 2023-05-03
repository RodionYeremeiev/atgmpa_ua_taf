package com.epam;

import com.epam.pages.DashBoardsPage;
import com.epam.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Disabled;

@Slf4j
public class LoginTests {

    private final LoginPage login = new LoginPage();
    private final DashBoardsPage dashBoardsPage = new DashBoardsPage();
    private final WebDriverManager driverManager = new WebDriverManager();

    @BeforeEach
    public void setup() {
        driverManager.openReportPortal();
    }

    @AfterEach
    public void tearDown() {
        driverManager.tearDown();
    }

    @Test
    @DisplayName("Login With valid credentials Test")
    @Disabled
        public void loginTest() {
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        log.info("Opening Report Portal Login Page");
        login.enterUserName(username).enterPassword(password).clickLoginButton();
        dashBoardsPage.waitWhileReady();
    }
}
