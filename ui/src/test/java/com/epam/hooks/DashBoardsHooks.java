package com.epam.hooks;

import com.epam.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DashBoardsHooks {
    WebDriverManager driverManager = new WebDriverManager();

    @Before("@dasboards_scenario")
    public void setup(){
        driverManager.setup();
    }

    @After("@dasboards_scenario")
    public void tearDown(){
        driverManager.tearDown();
    }
}
