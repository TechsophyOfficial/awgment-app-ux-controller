package com.techsophy.tsf.wrapperservice.controller.impl;

import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.CommentController;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.CommentDTO;
import com.techsophy.tsf.wrapperservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * wrapper apis for comment controller
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommentControllerImpl implements CommentController
{
    private final CommentService commentService;

    /**
     * create comments wrapper api
     * @param comment
     * @return ApiResponse
     */
    @Override
    public ApiResponse<Map<String, Object>> createComment(CommentDTO comment)
    {
        return new ApiResponse<>(this.commentService.createComment(comment), true, MessageConstants.CREATE_COMMENT_SUCCESS);
    }

    /**
     * get commnets for wrapper api
     * @param processInstanceId
     * @param caseInstanceId
     * @param businessKey
     * @return ApiResponse
     */
    @Override
    public ApiResponse<Object> getComments(String processInstanceId,String caseInstanceId, String businessKey)
    {
        return new ApiResponse<>(this.commentService.getComments(processInstanceId,caseInstanceId, businessKey), true, MessageConstants.GET_COMMENTS_SUCCESS);
    }
}
