package com.epam;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.epam.pages.DashBoardsPage;
import com.epam.pages.LoginPage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class UiTestsTestNg {

    private final LoginPage login = new LoginPage();
    private final DashBoardsPage dashBoardsPage = new DashBoardsPage();

    @Test(testName = "TestNG UI Test")
    public void test() {
        log.info("Opening Report Portal Login Page");
        open("https://reportportal.epam.com");
        login.withUserName("TESTUSERATGMPA").withPassword("Test1111").login();
        dashBoardsPage.waitWhileReady();
    }

    private void clickOnElement(SelenideElement element){
        actions().moveToElement(element).click().pause(1000).build().perform();
    }
}
