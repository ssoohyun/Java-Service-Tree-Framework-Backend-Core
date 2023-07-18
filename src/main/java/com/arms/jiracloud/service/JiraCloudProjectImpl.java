package com.arms.jiracloud.service;

import com.arms.jiracloud.config.JiraCloudConfig;
import com.egovframework.javaservice.treeframework.service.TreeServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service("jiraCloudProject")
public class JiraCloudProjectImpl extends TreeServiceImpl implements JiraCloudProject {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("jiraCloudConfig")
    private JiraCloudConfig jiraCloudConfig;

    @Override
    @Transactional
    public String miningDataToaRMS() throws Exception {
        final WebClient jiraWebClient = jiraCloudConfig.getJiraWebClient();

        String endpoint = "/rest/api/2/project/search";

        Flux<String> projects = jiraWebClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToFlux(String.class);

        projects.subscribe(
                project -> logger.info("Project: {}", project),
                error -> logger.error("Failed to retrieve projects: {}", error.getMessage()),
                () -> logger.info("Project retrieval completed.")
        );

        return "Jira Cloud Project Data Mining Complete";
    }

}