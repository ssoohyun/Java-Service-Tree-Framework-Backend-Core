package com.arms.filerepository.model;

import com.egovframework.javaservice.treeframework.model.TreeBaseDTO;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileRepositoryDTO extends TreeBaseDTO {

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

}
