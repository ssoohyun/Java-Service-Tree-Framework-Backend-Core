package com.arms.jiracloud.issue.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JiraCloudIssueDTO {
    private ProjectDTO project;
    private IssueTypeDTO issuetype;
    private String summary;
    private String description;
}
