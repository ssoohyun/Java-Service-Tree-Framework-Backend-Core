package com.arms.jiracloud.issue.service;

import com.egovframework.javaservice.treeframework.service.TreeService;

public interface JiraCloudIssue extends TreeService {

    public String miningDataToaRMS() throws Exception;

    public String makeIssueForReqAdd() throws Exception;
}
