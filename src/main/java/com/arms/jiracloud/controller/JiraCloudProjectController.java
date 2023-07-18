package com.arms.jiracloud.controller;

import com.arms.jiracloud.model.JiraCloudProjectDTO;
import com.arms.jiracloud.service.JiraCloudProject;
import com.arms.jiraproject.model.JiraProjectEntity;
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
@RequestMapping(value = {"/arms/jiraCloudProject"})
public class JiraCloudProjectController extends TreeAbstractController<JiraCloudProject, JiraCloudProjectDTO, JiraProjectEntity> {

    @Autowired
    @Qualifier("jiraCloudProject")
    private JiraCloudProject jiraCloudProject;

    @PostConstruct
    public void initialize() {
        setTreeService(jiraCloudProject);
        setTreeEntity(JiraProjectEntity.class);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(
            value = {"/miningDataToaRMS.do"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity<?> miningDataToaRMS(ModelMap model, HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(CommonResponse.success(jiraCloudProject.miningDataToaRMS()));
    }

}
