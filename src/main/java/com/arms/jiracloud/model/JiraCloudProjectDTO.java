package com.arms.jiracloud.model;

import com.egovframework.javaservice.treeframework.model.TreeBaseDTO;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JiraCloudProjectDTO extends TreeBaseDTO {


    private String c_jira_contents;

    private String c_jira_etc;

    private String c_jira_url;

    private String c_jira_id;

    private String c_jira_key;

    private String c_jira_name;


    private String c_jira_avatar_48;

    private String c_jira_avatar_32;

    private String c_jira_avatar_24;

    private String c_jira_avatar_16;

    private String c_jira_category_url;

    private String c_jira_category_id;

    private String c_jira_category_name;

    private String c_jira_category_desc;

}
