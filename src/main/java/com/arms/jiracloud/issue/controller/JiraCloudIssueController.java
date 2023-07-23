package com.arms.jiracloud.issue.controller;

import com.arms.jiracloud.issue.service.JiraCloudIssue;
import com.arms.jiraissue.model.JiraIssueDTO;
import com.arms.jiraissue.model.JiraIssueEntity;
import com.egovframework.javaservice.treeframework.controller.CommonResponse;
import com.egovframework.javaservice.treeframework.controller.TreeAbstractController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(value = {"/arms/jiraCloudIssue"})
public class JiraCloudIssueController extends TreeAbstractController<JiraCloudIssue, JiraIssueDTO, JiraIssueEntity> {

    @Autowired
    @Qualifier("jiraCloudIssue")
    private JiraCloudIssue jiraCloudIssue;

    @PostConstruct
    public void initialize() {
        setTreeService(jiraCloudIssue);
        setTreeEntity(JiraIssueEntity.class);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(
            value = {"/miningDataToaRMS.do"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity<?> miningDataToaRMS(ModelMap model, HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(CommonResponse.success(jiraCloudIssue.miningDataToaRMS()));
    }

    @ResponseBody
    @RequestMapping(
            value = {"/makeIssueForReqAdd.do"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<?> makeIssueForReqAdd(ModelMap model, HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(CommonResponse.success(jiraCloudIssue.makeIssueForReqAdd()));
    }

}
