package com.epam;


import com.epam.pages.DashBoardsPage;
import com.epam.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class LoginTests {

    private final LoginPage login = new LoginPage();
    private final DashBoardsPage dashBoardsPage = new DashBoardsPage();
    private final SessionManager sessionManager = new SessionManager();

    @BeforeEach
    public void setup() {
        sessionManager.openReportPortal();
    }

    @AfterEach
    public void tearDown() {
        sessionManager.tearDown();
    }

    @Test
    @DisplayName("Login With valid credentials Test")
        public void loginTest() {
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        log.info("Opening Report Portal Login Page");
        login.withUserName(username).withPassword(password).login();
        dashBoardsPage.waitWhileReady();
    }
}
