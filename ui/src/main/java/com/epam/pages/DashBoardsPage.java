package com.epam.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DashBoardsPage {
    private final SelenideElement wrapper = $("[class^=pageLayout]");
    private final SelenideElement addButton = $("[class^=addDashboardButton]");
    private final SelenideElement title = $("[title=\"All Dashboards\"]");

    public void waitWhileReady() {
        wrapper.should(exist);
        title.shouldBe(visible);
        addButton.shouldBe(visible, enabled);
    }
}
