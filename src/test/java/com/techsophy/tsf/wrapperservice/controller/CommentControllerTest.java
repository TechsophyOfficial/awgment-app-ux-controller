package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.impl.CommentControllerImpl;
import com.techsophy.tsf.wrapperservice.dto.ApiResponse;
import com.techsophy.tsf.wrapperservice.dto.CommentDTO;
import com.techsophy.tsf.wrapperservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.HashMap;
import java.util.Map;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.BASEURL;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.COMMENTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    CommentService commentService;

    @InjectMocks
    CommentControllerImpl commentController;

    @Test
    void createCommentTest(){
        CommentDTO commentDTO = new CommentDTO("task_id", "p_id", "b_key", "test_comment");
        Mockito.when(commentService.createComment(commentDTO)).thenReturn(Map.of("key", "val"));
        ApiResponse<Map<String, Object>> actualOutput = commentController.createComment(commentDTO);
        ApiResponse<Map<String, Object>> expectedOutput = new ApiResponse<>(commentService.createComment(commentDTO), true, MessageConstants.CREATE_COMMENT_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getCommentsTest(){
        String processInstanceId = "p_id", caseInstanceId = "c_id", businessKey = "b_key";
        Mockito.when(commentService.getComments(processInstanceId,caseInstanceId, businessKey)).thenReturn("object");
        ApiResponse<Object> actualOutput = commentController.getComments(processInstanceId, caseInstanceId, businessKey);
        ApiResponse<Object> expectedOutput = new ApiResponse<>(commentService.getComments(processInstanceId,caseInstanceId, businessKey), true, MessageConstants.GET_COMMENTS_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
