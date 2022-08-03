package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants;
import com.techsophy.tsf.wrapperservice.controller.impl.CommentControllerImpl;
import com.techsophy.tsf.wrapperservice.dto.CommentDTO;
import com.techsophy.tsf.wrapperservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@ExtendWith({MockitoExtension.class})
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    CommentService commentService;
    @InjectMocks
    CommentControllerImpl commentController;
    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }
//    @Test
//    void createComment() throws Exception
//    {   ObjectMapper objectMapperTest=new ObjectMapper();
//        Map<String,Object> map = new HashMap<>();
//        CommentDTO commentDTO = new CommentDTO("abc","abc","abc","abc");
//        when(commentService.createComment(any())).thenReturn(map);
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.post(BASEURL + ApplicationEndpointConstants.VERSION_1 + COMMENTS)
//                .content(objectMapperTest.writeValueAsString(commentDTO)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }
//    @Test
//    void getCommentsTest() throws Exception
//    {
//
//        ObjectMapper objectMapperTest = new ObjectMapper();
//        Mockito.when(commentService.getComments("abc","abc","abc")).thenReturn(new Object());
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.get(ApplicationEndpointConstants.BASEURL+ ApplicationEndpointConstants.VERSION_1+ApplicationEndpointConstants.COMMENTS,1)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }
}
