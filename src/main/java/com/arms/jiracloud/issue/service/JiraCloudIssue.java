package com.arms.jiracloud.issue.service;

import com.arms.jiracloud.issue.model.JiraCloudIssueInputDTO;
import com.egovframework.javaservice.treeframework.service.TreeService;

public interface JiraCloudIssue extends TreeService {

    public String miningDataToaRMS() throws Exception;

    public String makeIssueForReqAdd(JiraCloudIssueInputDTO jiraCloudIssueInputDTO) throws Exception;
}