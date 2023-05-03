package com.epam.pages;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.CollectionCondition;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

@Slf4j
public class DashBoardsPage {
    private final SelenideElement wrapper = $("[class^=pageLayout]");
    private final SelenideElement addButton = $("[class^=addDashboardButton]");
    private final SelenideElement title = $("[title=\"All Dashboards\"]");
    private final SelenideElement addNewDashboardModalHeader = $(byText("Add New Dashboard"));
    private final SelenideElement enterDashboardNameInput =
            $(Selectors.byAttribute("placeholder", "Enter dashboard name"));
    private final SelenideElement addButtonModal = $(byText("Add"));
    private final ElementsCollection existingDashBoards = $$("[class*=dashboardTable__name]");
    private final ElementsCollection widgetNames = $$("[class*=widget-name-block]");
    private final SelenideElement addNewWidgetButton = $(byText("Add new widget"));
    private final ElementsCollection widgetTypes = $$("[class*=widget-type-item-name]");
    private final SelenideElement nextStepButton = $(byText("Next step"));
    private final SelenideElement demoFilterRadio = $(byText("DEMO_FILTER"));
    private final SelenideElement errorInput = $("[class*=input__error]");

    public void waitWhileReady() {
        wrapper.should(exist);
        title.shouldBe(visible);
        addButton.shouldBe(visible, enabled);
    }

    public DashBoardsPage addNewDashBoard() {
        String name = RandomStringUtils.randomNumeric(6);
        addButton.click();
        addNewDashboardModalHeader.shouldBe(visible);
        enterDashboardNameInput.setValue(name);
        addButtonModal.click();
        $(byTitle(name)).shouldBe(visible);
        return this;
    }

    public DashBoardsPage clickAddNewDashBoardButton(){
        addButton.click();
        addNewDashboardModalHeader.shouldBe(visible);
        return this;
    }

    public DashBoardsPage inputDashBoardName(String name){
        enterDashboardNameInput.setValue(name);
        return this;
    }

    public DashBoardsPage clickAddButtonModal(){
        addButtonModal.click();
        return this;
    }

    public void checkDashBoardPresent(String name){
        $(byTitle(name)).shouldBe(visible);
    }

    public DashBoardsPage selectFirstDashBoard() {
        existingDashBoards.shouldBe(CollectionCondition.sizeGreaterThan(0)).get(0).click();
        return this;
    }

    public DashBoardsPage clickAddWidgetButton() {
        addNewWidgetButton.shouldBe(enabled).click();
        return this;
    }

    public DashBoardsPage selectWidgetType(int index) {
        widgetTypes.shouldHaveSize(21).get(index).click();
        return this;
    }

    public DashBoardsPage clickNextStepButton() {
        nextStepButton.hover().shouldBe(enabled).click();
        return this;
    }

    public DashBoardsPage selectWidgetDemoFilter() {
        demoFilterRadio.shouldBe(visible).click();
        return this;
    }

    public DashBoardsPage saveWidget() {
        addButtonModal.shouldBe(enabled).click();
        $(byText("Widget has been added")).shouldBe(visible);
        return this;
    }

    public ElementsCollection existingWidgets() {
        return widgetNames.shouldHave(CollectionCondition.sizeGreaterThan(1));
    }

    public void checkErrorPresent() {
        errorInput.shouldBe(visible);
    }
}
