package com.techsophy.tsf.wrapperservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityLinksDto {
    private String userId;
    private String groupId;
    private String type;
}
