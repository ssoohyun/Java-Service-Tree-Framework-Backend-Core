package com.arms.jiracloud.issue.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JiraCloudIssueInputDTO {
    private JiraCloudIssueDTO fields;
}
