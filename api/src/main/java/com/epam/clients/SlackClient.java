package com.epam.clients;

import com.epam.models.SlackMessageBody;
import com.slack.api.Slack;
import lombok.SneakyThrows;

import static com.epam.config.ApiConfig.SLACK_TOKEN;
import static com.epam.config.ApiConfig.SLACK_WEB_HOOK_URL;

public class SlackClient {

    @SneakyThrows
    public void sendSlackMessage(String message) {
        SlackMessageBody body = SlackMessageBody.builder().text(message).build();
        Slack.getInstance().getHttpClient().postCamelCaseJsonBodyWithBearerHeader(SLACK_WEB_HOOK_URL, SLACK_TOKEN, body);
    }
}
