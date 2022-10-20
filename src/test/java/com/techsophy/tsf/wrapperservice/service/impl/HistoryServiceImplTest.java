package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.dto.HistoryDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryDataResponseDTO;
import com.techsophy.tsf.wrapperservice.dto.HistoryResponseDTO;
import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import com.techsophy.tsf.wrapperservice.utils.WebClientWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest {
    @Mock
    WebClientWrapper webClientWrapper;
    @Mock
    TokenUtils accountUtils;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    GlobalMessageSource globalMessageSource;

    @InjectMocks
    HistoryServiceImpl historyService;

    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;
    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(historyService, "camundaServletContextPath", "http://apigateway.techsophy.com");
        ReflectionTestUtils.setField(historyService, "gatewayURI", "http://apigateway.techsophy.com");
    }

    @Test
    void historyCountTest() throws JsonProcessingException {
        List list = List.of(Map.of("key", "val"));
        HistoryDTO dto = new HistoryDTO();
        dto.setSorting(list);
        dto.setTaskAssignee("assignee");
        dto.setFinished(true);
        String response = "response";

        Mockito.when(webClientWrapper.webclientRequest(any(), anyString(), anyString(), any(HistoryDTO.class))).thenReturn(response);

        HistoryResponseDTO actualOutput = historyService.historyCount(dto);
        HistoryResponseDTO expectedOutput = objectMapper.readValue(response, HistoryResponseDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void historyDataTestWhileThrowingException() throws JsonProcessingException {
        List list = List.of(Map.of("key", "val"));
        HistoryDTO dto = new HistoryDTO();
        dto.setSorting(list);
        dto.setTaskAssignee("assignee");
        dto.setFinished(true);

        Assertions.assertThrows(InvalidInputException.class, () -> historyService.historyCount(dto));
    }
}