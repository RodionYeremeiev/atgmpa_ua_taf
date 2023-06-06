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
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Condition;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

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
    public final SelenideElement search = $("input[class*=inputSearch__input]");
    private final ElementsCollection widgets = $$("[class*=react-grid-item]");
    private final SelenideElement currentProjectSelector = $("[class*=sidebar__main-block]");
    private final SelenideElement currentProjectsPopup = $("[class*=projects-list]");
    private final ElementsCollection currentProjectElements = $$("[class*=project-list-item]");

    public void waitWhileReady() {
        wrapper.should(exist);
        title.shouldBe(visible);
        addButton.shouldBe(visible, enabled);
    }

    public DashBoardsPage selectTestProject() {
        currentProjectSelector.click();
        currentProjectsPopup.shouldBe(visible);
        SelenideElement testUserProject = currentProjectElements.stream()
                .filter(
                        element -> element.getAttribute("href").contains(System.getProperty("username").toLowerCase()))
                .findFirst().get();
        testUserProject.click();
        return this;
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

    public void checkErrorPresent() {
        errorInput.shouldBe(visible);
    }

    public ElementsCollection getWidgets() {
        widgets.shouldBe(
                CollectionCondition.allMatch("Not all widgets are displayed", WebElement::isDisplayed));
        return widgets;
    }

    public SelenideElement getFirstWidget(){
        SelenideElement firstWidget = getWidgets().get(0);
        firstWidget.shouldBe(Condition.visible);
        return firstWidget;
    }

    public void resizeWidget(SelenideElement widget, int xOffset, int yOffset) {
        Selenide.actions()
                .clickAndHold(widget.$("[class*=resizable-handle]"))
                .moveByOffset(xOffset, yOffset)
                .release()
                .build()
                .perform();
    }

    public void decreaseWidget(SelenideElement widget) {
        resizeWidget(widget, -75, 0);
    }

    public void increaseWidget(SelenideElement widget) {
        resizeWidget(widget, 75, 0);
    }

    public void selectDemoDashboard() {
        Selenide.actions()
                .sendKeys(search, "DEMO", Keys.ENTER)
                .pause(Duration.of(1, ChronoUnit.SECONDS))
                .build()
                .perform();
        selectFirstDashBoard();
    }
}
