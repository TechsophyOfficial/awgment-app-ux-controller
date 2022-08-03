package com.techsophy.tsf.wrapperservice.controller;

import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.CommentDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.*;

/**
 * Interface for comment controller
 */
@RequestMapping(BASEURL+VERSION_1)
public interface CommentController
{
    /**
     * create comment wrapper api
     * @param comment
     * @return ApiResponse
     */
    @PostMapping(COMMENTS)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Map<String, Object>> createComment(@RequestBody CommentDTO comment);

    /**
     * get comments by process instance id or case instance id or business key
     * @param processInstanceId
     * @param caseInstanceId
     * @param businessKey
     * @return ApiResponse
     */
    @GetMapping(COMMENTS)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<Object> getComments(@RequestParam(required = false) String processInstanceId, @RequestParam(required = false) String caseInstanceId,@RequestParam(required = false) String businessKey);
}
