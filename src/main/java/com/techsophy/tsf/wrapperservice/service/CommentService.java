package com.techsophy.tsf.wrapperservice.service;

import com.techsophy.tsf.wrapperservice.dto.CommentDTO;
import java.util.Map;

/**
 * comment service
 */
public interface CommentService
{
    /**
     * create comment
     * @param comment
     * @return Map
     */
    Map<String, Object> createComment(CommentDTO comment) ;

    /**
     * get all comments
     * @param processInstanceId
     * @param caseInstanceId
     * @param businessKey
     * @return
     */
    Object getComments(String processInstanceId,String caseInstanceId, String businessKey);
}
