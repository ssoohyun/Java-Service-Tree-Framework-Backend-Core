package com.arms.jiracloud.issue.service;

import com.arms.jiracloud.config.JiraCloudConfig;
import com.arms.jiracloud.issue.model.JiraCloudIssueInputDTO;
import com.egovframework.javaservice.treeframework.service.TreeServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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
    public String makeIssueForReqAdd(JiraCloudIssueInputDTO jiraCloudIssueInputDTO) throws Exception {

        final WebClient jiraWebClient = jiraCloudConfig.getJiraWebClient();

        String endpoint = "/rest/api/2/issue";

        // create an issue
        JiraCloudIssueInputDTO addJiraCloudIssue = jiraWebClient.post()
                .uri(endpoint)
                .bodyValue(jiraCloudIssueInputDTO)
                .retrieve()
                .bodyToMono(JiraCloudIssueInputDTO.class)
                .block();

        return "Jira Cloud Issue Data Create Complete";
    }

}