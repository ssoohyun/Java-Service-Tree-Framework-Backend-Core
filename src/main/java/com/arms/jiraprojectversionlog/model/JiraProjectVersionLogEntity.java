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
package com.arms.jiraprojectversionlog.model;

import com.egovframework.ple.treeframework.model.TreeBaseEntity;
import com.egovframework.ple.treeframework.model.TreeLogBaseEntity;
import com.egovframework.ple.treeframework.model.TreeSearchEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "T_ARMS_JIRAPROJECTVERSION_LOG")
@SelectBeforeUpdate(value=true)
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Cache(usage = CacheConcurrencyStrategy.NONE)
public class JiraProjectVersionLogEntity extends TreeLogBaseEntity implements Serializable {

    public JiraProjectVersionLogEntity() {
        super();
    }

    public JiraProjectVersionLogEntity(Boolean copyBooleanValue) {
        super();
        this.copyBooleanValue = copyBooleanValue;
    }

 	@Override
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "c_id")
    public Long getC_id() {
        return super.getC_id();
    }

    //@Getter @Setter

    @Column(name = "c_jira_link")
    private Long c_jira_link;

    @Column(name = "c_jira_version_url")
    @Type(type="text")
    private String c_jira_version_url;

    @Column(name = "c_jira_version_id")
    @Type(type="text")
    private String c_jira_version_id;

    @Column(name = "c_jira_version_desc")
    @Type(type="text")
    private String c_jira_version_desc;

    @Column(name = "c_jira_version_name")
    @Type(type="text")
    private String c_jira_version_name;

    @Column(name = "c_jira_version_projectid")
    @Type(type="text")
    private String c_jira_version_projectid;

    @Column(name = "c_jira_version_archived")
    @Type(type="text")
    private String c_jira_version_archived;

    @Column(name = "c_jira_version_released")
    @Type(type="text")
    private String c_jira_version_released;

    @Column(name = "c_jira_version_releaseDate")
    @Type(type="text")
    private String c_jira_version_releaseDate;

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
