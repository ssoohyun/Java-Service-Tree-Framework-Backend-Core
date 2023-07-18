/*
 * @author Dongmin.lee
 * @since 2023-03-21
 * @version 23.03.21
 * @see <pre>
 *  Copyright (C) 2007 by 313 DEV GRP, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by 313 developer group <313@313.co.kr>, December 2010
 * </pre>
 */
package com.arms.jiracloud.model;

import com.arms.jiraprojectversion.model.JiraProjectVersionEntity;
import com.egovframework.javaservice.treeframework.model.TreeBaseEntity;
import com.egovframework.javaservice.treeframework.model.TreeSearchEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table(name = "T_ARMS_JIRAPROJECT")
@SelectBeforeUpdate(value=true)
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Cache(usage = CacheConcurrencyStrategy.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class JiraCloudProjectEntity extends TreeSearchEntity implements Serializable {

 	@Override
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "c_id")
    public Long getC_id() {
        return super.getC_id();
    }

    //@Getter @Setter

    @Lob
    @Column(name = "c_jira_contents")
    private String c_jira_contents;

    @Column(name = "c_jira_etc")
    @Type(type="text")
    private String c_jira_etc;

    @Column(name = "c_jira_url")
    @Type(type="text")
    private String c_jira_url;

    @Column(name = "c_jira_id")
    @Type(type="text")
    private String c_jira_id;

    @Column(name = "c_jira_key")
    @Type(type="text")
    private String c_jira_key;

    @Column(name = "c_jira_name")
    @Type(type="text")
    private String c_jira_name;


    @Column(name = "c_jira_avatar_48")
    @Type(type="text")
    private String c_jira_avatar_48;

    @Column(name = "c_jira_avatar_32")
    @Type(type="text")
    private String c_jira_avatar_32;

    @Column(name = "c_jira_avatar_24")
    @Type(type="text")
    private String c_jira_avatar_24;

    @Column(name = "c_jira_avatar_16")
    @Type(type="text")
    private String c_jira_avatar_16;

    @Column(name = "c_jira_category_url")
    @Type(type="text")
    private String c_jira_category_url;

    @Column(name = "c_jira_category_id")
    @Type(type="text")
    private String c_jira_category_id;

    @Column(name = "c_jira_category_name")
    @Type(type="text")
    private String c_jira_category_name;

    @Column(name = "c_jira_category_desc")
    @Type(type="text")
    private String c_jira_category_desc;


    // -- 1:N table 연계
    private Set<JiraProjectVersionEntity> jiraProjectVersionEntities;

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "GLOBAL_TREE_MAP",
            joinColumns = @JoinColumn(name = "jiraproject_link"),
            inverseJoinColumns = @JoinColumn(name = "jiraprojectversion_link")
    )
    @WhereJoinTable( clause = "pdserviceversion_link is not null")
    public Set<JiraProjectVersionEntity> getJiraProjectVersionEntities() {
        return jiraProjectVersionEntities;
    }

    public void setJiraProjectVersionEntities(Set<JiraProjectVersionEntity> jiraProjectVersionEntities) {
        this.jiraProjectVersionEntities = jiraProjectVersionEntities;
    }

    /*
     * Extend Bean Field
     */
	@JsonIgnore
    private Boolean copyBooleanValue;

    @Transient
	@ApiModelProperty(hidden = true)
    public Boolean getCopyBooleanValue() {
        copyBooleanValue = false;
        if (this.getCopy() == 0) {
            copyBooleanValue = false;
        } else {
            copyBooleanValue = true;
        }
        return copyBooleanValue;
    }

    public void setCopyBooleanValue(Boolean copyBooleanValue) {
        this.copyBooleanValue = copyBooleanValue;
    }

    @Override
    public <T extends TreeSearchEntity> void setFieldFromNewInstance(T paramInstance) {
        if( paramInstance instanceof TreeBaseEntity){
            if(paramInstance.isCopied()) {
                this.setC_title("copy_" + this.getC_title());
            }
        }
    }
}
