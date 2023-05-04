Feature: User can login to Report Portal

  Background: Open report Portal
    Given open Report Portal page

  Scenario: Valid credentials
    When user provide valid credentials
    Then Login successful