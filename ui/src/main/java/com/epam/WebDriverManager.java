package com.epam;

import com.codeborne.selenide.Selenide;
import com.epam.pages.DashBoardsPage;
import com.epam.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class WebDriverManager {

    public void setup() {
        log.info("Login to Portal Login Page and open DashBoards page");
        open("https://reportportal.epam.com");
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        new LoginPage().enterUserName(username).enterPassword(password).clickLoginButton();
        DashBoardsPage dashBoardsPage = new DashBoardsPage();
        dashBoardsPage.waitWhileReady();
        dashBoardsPage.selectTestProject();
    }

    public void tearDown() {
        Selenide.closeWebDriver();
    }

    public void openReportPortal(){
        open("https://reportportal.epam.com");
    }
}
