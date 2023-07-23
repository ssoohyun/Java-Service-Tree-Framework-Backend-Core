package com.arms.jiracloud.issue.service;

import com.arms.jiracloud.config.JiraCloudConfig;
import com.egovframework.javaservice.treeframework.service.TreeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service("jiraCloudIssue")
public class JiraCloudIssueImpl extends TreeServiceImpl implements JiraCloudIssue {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("jiraCloudConfig")
    private JiraCloudConfig jiraCloudConfig;

    @Override
    @Transactional
    public String miningDataToaRMS() throws Exception {

        final WebClient jiraWebClient = jiraCloudConfig.getJiraWebClient();

        String issueKey = "TEST-1";
        String endpoint = "/rest/api/2/issue/" + issueKey;

        Flux<String> issue = jiraWebClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToFlux(String.class);

        issue.subscribe(
                response -> logger.info("Issue: {}", response),
                error -> logger.error("Failed to retrieve issue: {}", error.getMessage()),
                () -> logger.info("Issue retrieval completed.")
        );

        return "Jira Cloud Issue Data Mining Complete";

    }

    @Override
    @Transactional
    public String makeIssueForReqAdd() throws Exception {

        final WebClient jiraWebClient = jiraCloudConfig.getJiraWebClient();

        String endpoint = "/rest/api/2/issue";

        // JSON data for create issue
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> fieldsMap = new HashMap<>();
        Map<String, Object> projectMap = new HashMap<>();
        Map<String, Object> issueTypeMap = new HashMap<>();

        projectMap.put("key", "TEST");
        issueTypeMap.put("name", "Task");

        fieldsMap.put("project", projectMap);
        fieldsMap.put("issuetype", issueTypeMap);
        fieldsMap.put("summary", "이슈 생성 테스트");
        fieldsMap.put("description", "이슈 상세 내용입니다!");

        jsonMap.put("fields", fieldsMap);

        String json = objectMapper.writeValueAsString(jsonMap);

        // create an issue
        Mono<String> responseMono = jiraWebClient.post()
                .uri(endpoint)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(json)
                .retrieve()
                .bodyToMono(String.class);

        responseMono.subscribe(
                response -> logger.info("Response: {}", response),
                error -> logger.error("Failed to create an issue: {}",  error.getMessage())
        );

        return "Jira Cloud Issue Data Create Complete";
    }

}