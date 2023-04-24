package com.epam.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.name;

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

    public LoginPage withUserName(String userName){
        waitWhileReady();
        loginField.setValue(userName);
        return this;
    }

    public LoginPage withPassword(String passWord){
        waitWhileReady();
        passwordField.setValue(passWord);
        return this;
    }

    public LoginPage login(){
        waitWhileReady();
        wrapper.$(loginButton).click();
        return this;
    }
}
