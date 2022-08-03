package com.techsophy.tsf.wrapperservice.dto;

import lombok.Data;

import java.util.Map;
@Data
public class GroupCaseInstanceDTO {
    Map<String,Object> data;
    Map<String,Object> properties;
    String type;
    String version;
    String isDefault;
    String createdById;
    String createdOn;
    String createdByName;
    String updatedById;
    String updatedOn;
    String updatedByName;

}
