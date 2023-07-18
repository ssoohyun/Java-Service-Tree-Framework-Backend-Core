package com.arms.jiracloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Component
public class JiraCloudConfig {
    @Value("${cloud.jira.baseurl}")
    public String jiraUrl;

    @Value("${cloud.jira.id}")
    public String jiraID;

    @Value("${cloud.jira.pass}")
    public String jiraPass;

    @Bean
    public WebClient getJiraWebClient() {
        return WebClient.builder()
                .baseUrl(jiraUrl)
                .defaultHeader("Authorization", "Basic " + getBase64Credentials(jiraID, jiraPass))
                .build();
    }

    private String getBase64Credentials(String jiraID, String jiraPass) {
        String credentials = jiraID + ":" + jiraPass;
        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }

}
