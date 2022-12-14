package com.techsophy.tsf.wrapperservice.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommonUtilsTest {
    @InjectMocks
    CommonUtils commonUtils;
    @Test
    void isValidStringTest() throws Exception
    {
        String value = "abc";
        boolean actualResult = CommonUtils.isValidString(value);
        boolean expectedResult = value != null && !value.isEmpty() && !value.isBlank();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
