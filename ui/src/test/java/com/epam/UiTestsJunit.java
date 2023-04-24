package com.epam;

import com.epam.pages.DashBoardsPage;
import com.epam.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class UiTestsJunit {
    private final LoginPage login = new LoginPage();
    private final DashBoardsPage dashBoardsPage = new DashBoardsPage();

    @Test
    @DisplayName("UI test Stub")
    public void testAA() {
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        log.info("Opening Report Portal Login Page");
        open("https://reportportal.epam.com");
        login.withUserName(username).withPassword(password).login();
        dashBoardsPage.waitWhileReady();
    }
}
