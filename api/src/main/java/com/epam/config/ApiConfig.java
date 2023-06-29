package com.epam.config;

public class ApiConfig {
    public static final String REPORT_PORTAL_BASE_URL = "https://rp.epam.com/api/v1";
    public static final String ZEPHYR_API_BASE_URL = "https://api.zephyrscale.smartbear.com/v2";

    public static final String REPORT_PORTAL_TOKEN = System.getProperty("reportPortalToken");
    public static final String SLACK_TOKEN = System.getProperty("slackToken");

    public static final String SLACK_WEB_HOOK_URL = "https://hooks.slack.com/services/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";

    public static final String DASH_BOARDS_END_POINT = "/testuseratgmpa_personal/dashboard";
}
