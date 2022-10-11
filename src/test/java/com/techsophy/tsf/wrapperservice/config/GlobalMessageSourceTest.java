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

    @Test
    void getSingleArgTest() {
        Mockito.when(messageSource.getMessage(any(),any(),any())).thenReturn("key");
        String actualOutput=globalMessageSource.get("key");
        String expectedOutput = messageSource.getMessage("key", null, LocaleContextHolder.getLocale());
        Assertions.assertSame(expectedOutput, actualOutput);

    }

    @Test
    void getDoubleArgTest() {
        Mockito.when(messageSource.getMessage(any(),any(),any())).thenReturn("key");
        String actualOutput = globalMessageSource.get("key", "ARG");
        String expectedOutput = messageSource.getMessage("key", new Object[]{"ARG"}, LocaleContextHolder.getLocale());
        Assertions.assertSame(expectedOutput, actualOutput);
    }
}