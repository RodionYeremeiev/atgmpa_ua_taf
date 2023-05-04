
Feature: Dashboards feature tests

  @dasboards_scenario
  Scenario: User can add new dashboard
    When user click add new dashboard button
    And user input dashBoardName
    And user click add dashboard modal button
    Then new dash board added to dashboards page

  @dasboards_scenario
  Scenario: User can add widget on dashboard
    When user select first dashboard on dashboards page
    And user click add widget button
    And user select widget type
    And user click next step button
    And user select demo filter widget
    And user click next step button
    And user click save widget button
    Then user widget added message appears

  @dasboards_scenario
  Scenario Outline: User can not create <name> dashboard
    When user click add new dashboard button
    But user provide a dashboard name with invalid length <length>
    And user click add dashboard modal button
    Then Error message is present
    Examples:
      |           name            |   length    |
      |"Name less than 3 chars"   |      2      |
      |"Name more than 128 chars" |     130     |