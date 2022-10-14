package com.techsophy.tsf.wrapperservice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GlobalMessageSourceTest {

    @Mock
    MessageSource messageSource;

    @InjectMocks
    GlobalMessageSource globalMessageSource;

    String key = "key";

    @Test
    void getSingleArgTest() {
        String expectedOutput = key;
        Mockito.when(messageSource.getMessage(any(),any(),any())).thenReturn(expectedOutput);
        String actualOutput=globalMessageSource.get(key);
        Assertions.assertSame(expectedOutput, actualOutput);
    }

    @Test
    void getDoubleArgTest() {
        String expectedOutput = key;
        Mockito.when(messageSource.getMessage(any(),any(),any())).thenReturn(key);
        String actualOutput = globalMessageSource.get(key, "ARG");
        Assertions.assertSame(expectedOutput, actualOutput);
    }
}