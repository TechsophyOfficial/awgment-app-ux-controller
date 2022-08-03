package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants;
import com.techsophy.tsf.wrapperservice.controller.impl.ProcessControllerImpl;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.*;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.ENGINE_REST;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.UPDATE_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@ExtendWith({MockitoExtension.class})
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcessControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    ProcessService mockProcessService;
    @InjectMocks
    ProcessControllerImpl processController;
    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }
    @Test
    void getTaskByIdTest()
    {
        processController.getTaskById("1");
    }
//    @Test
//    void completeActiveTaskByBusinessKeyTest() throws Exception
//    {
//        Map<String,Object> map = new HashMap<>();
//        map.put("abc","abc");
//        ObjectMapper objectMapperTest=new ObjectMapper();
//        GenericDTO genericDTO=new GenericDTO("123","abc",map);
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.post(BASEURL+ VERSION_1+ApplicationEndpointConstants.COMPLETE_TASK)
//                .content(objectMapperTest.writeValueAsString(genericDTO)) .contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }
    //@Order(2)
    @Test
    void startProcessByDefinitionKeyTest()
    {
        Map<String,Object> variable=new HashMap<>();
        variable.put("abc","abc");
        ProcessInstance processInstance=new ProcessInstance("1","1",variable);
        processController.startProcessByDefinitionKey(processInstance);
    }
//    @Test
//    void getTasksTest() throws Exception
//    {
//        ObjectMapper objectMapperTest=new ObjectMapper();
//        TaskQueryDTO taskQueryDTO = new TaskQueryDTO();
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.post(BASEURL+VERSION_1+TASKS)
//                .content(objectMapperTest.writeValueAsString(taskQueryDTO)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }

//    @Test
//    void getTasksCountTest() throws Exception
//    {
//        ObjectMapper objectMapperTest=new ObjectMapper();
//        TaskCountDTO taskCountDTO = new TaskCountDTO();
//        TaskQueryDTO taskQueryDTO = new TaskQueryDTO();
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.post(BASEURL+VERSION_1+TASKS_COUNT)
//                .content(objectMapperTest.writeValueAsString(taskQueryDTO)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }
//    @Test
//    void claimTask() throws Exception
//    {
//        ObjectMapper objectMapperTest=new ObjectMapper();
//        UserTaskActivityWrapperDTO userTaskActivityWrapperDTO = new UserTaskActivityWrapperDTO("1","abc");
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.post(BASEURL+VERSION_1+ENGINE_REST + "/task/1/claim")
//                .content(objectMapperTest.writeValueAsString(userTaskActivityWrapperDTO)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }
//    @Test
//    void setAssigneeToTask() throws Exception
//    {
//        ObjectMapper objectMapperTest=new ObjectMapper();
//        UserTaskActivityWrapperDTO userTaskActivityWrapperDTO = new UserTaskActivityWrapperDTO("1","abc");
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.post(BASEURL+VERSION_1+ENGINE_REST + "/task/1/assignee")
//                .content(objectMapperTest.writeValueAsString(userTaskActivityWrapperDTO)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }
//    @Test
//    void resumeProcess() throws Exception
//    {
//        ObjectMapper objectMapperTest=new ObjectMapper();
//        ResumeProcessRequestDTO resumeProcessRequestDTO =new ResumeProcessRequestDTO();
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.post(BASEURL+VERSION_1+RESUME_PROCESS)
//                .content(objectMapperTest.writeValueAsString(resumeProcessRequestDTO)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }
    @Test
    void deploy()
    {
        MockMultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
        processController.deployProcess("abc",multipartFile);
    }

//    @Test
//    void updateTask() throws Exception
//    {
//        ObjectMapper objectMapperTest=new ObjectMapper();
//        UpdateTaskDto updateTaskDto = new UpdateTaskDto("20/04/2022","22/05/2022");
//        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.put(BASEURL+VERSION_1+ENGINE_REST+"/task/1234")
//                .content(objectMapperTest.writeValueAsString(updateTaskDto)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
//        assertEquals(200,mvcResult.getResponse().getStatus());
//    }
}
