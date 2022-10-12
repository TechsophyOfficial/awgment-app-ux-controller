package com.techsophy.tsf.wrapperservice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LocaleConfigTest {

    @Mock
    HttpServletRequest request;
    @InjectMocks
    LocaleConfig localeConfig;

    @Test
    void resolveLocaleTest() {
        Mockito.when(request.getHeader(any())).thenReturn("test");
        Locale actualOutput = localeConfig.resolveLocale(request);
        Locale expectedOutput = new Locale(request.getHeader(ACCEPT_LANGUAGE));
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void resolveLocaleEmptyHeaderTest() {
        Mockito.when(request.getHeader(any())).thenReturn("");
        Locale actualOutput = localeConfig.resolveLocale(request);
        Locale expectedOutput = Locale.US;
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void messageSourceTest() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasenames(BASENAME_ERROR_MESSAGES, BASENAME_MESSAGES);
        messageSource.setCacheMillis(CACHEMILLIS);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setUseCodeAsDefaultMessage(USEDEFAULTCODEMESSAGE);
        MessageSource messageSource1 = messageSource;

        MessageSource response = localeConfig.messageSource();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getClass(), messageSource1.getClass());
    }
}