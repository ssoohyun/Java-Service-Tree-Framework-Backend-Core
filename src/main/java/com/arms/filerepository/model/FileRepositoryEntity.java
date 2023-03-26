/*
 * @author Dongmin.lee
 * @since 2022-11-04
 * @version 22.11.04
 * @see <pre>
 *  Copyright (C) 2007 by 313 DEV GRP, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by 313 developer group <313@313.co.kr>, December 2010
 * </pre>
 */
package com.arms.filerepository.model;

import com.egovframework.ple.treeframework.model.TreeBaseEntity;
import com.egovframework.ple.treeframework.model.TreeSearchEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "T_ARMS_FILEREPOSITORY")
@SelectBeforeUpdate(value=true)
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileRepositoryEntity extends TreeSearchEntity implements Serializable {

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "c_id")
    public Long getC_id() {
        return super.getC_id();
    }

    //@Getter @Setter
    //필드명과 컬럼명이 다를 경우는 하기와 같이 처리.
    private String fileName;
    private String contentType;
    private String serverSubPath;
    private String physicalName;
    private Long size;
    private String name;
    private String url;
    private String thumbnailUrl;
    private String delete_url;
    private String delete_type;

    @Column(name="C_FILE_NAME")
    @Type(type="text")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name="C_CONTENT_TYPE")
    @Type(type="text")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Column(name="C_SERVER_SUB_PATH")
    @Type(type="text")
    public String getServerSubPath() {
        return serverSubPath;
    }

    public void setServerSubPath(String serverSubPath) {
        this.serverSubPath = serverSubPath;
    }

    @Column(name="C_PHYSICAL_NAME")
    @Type(type="text")
    public String getPhysicalName() {
        return physicalName;
    }

    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    @Column(name="C_SIZE")
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Column(name="C_NAME")
    @Type(type="text")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="C_URL")
    @Type(type="text")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name="C_THUMBNAIL_URL")
    @Type(type="text")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Column(name="C_DELETE_URL")
    @Type(type="text")
    public String getDelete_url() {
        return delete_url;
    }

    public void setDelete_url(String delete_url) {
        this.delete_url = delete_url;
    }

    @Column(name="C_DELETE_TYPE")
    @Type(type="text")
    public String getDelete_type() {
        return delete_type;
    }

    public void setDelete_type(String delete_type) {
        this.delete_type = delete_type;
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
