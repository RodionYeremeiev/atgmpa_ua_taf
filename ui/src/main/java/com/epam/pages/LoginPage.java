package com.epam.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.name;

@Slf4j
public class LoginPage {
    private final SelenideElement wrapper = $("[class^=loginPage] [class^=pageBlockContainer]");
    private final SelenideElement loginField = $(name("login"));
    private final SelenideElement passwordField = $(name("password"));
    private final String loginButton = "[type=submit]";

    public void waitWhileReady() {
        wrapper.should(exist);
        loginField.shouldBe(visible, enabled);
        passwordField.shouldBe(visible, enabled);
        wrapper.$(loginButton).shouldBe(visible, enabled);
    }

    public LoginPage enterUserName(String username){
        waitWhileReady();
        loginField.setValue(username);
        return this;
    }

    public LoginPage enterPassword(String password){
        waitWhileReady();
        passwordField.setValue(password);
        return this;
    }

    public void clickLoginButton(){
        waitWhileReady();
        wrapper.$(loginButton).click();
    }
}
