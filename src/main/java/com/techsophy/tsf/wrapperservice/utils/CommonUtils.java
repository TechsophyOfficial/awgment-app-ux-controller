package com.techsophy.tsf.wrapperservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * common utils to check string is valid or not
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils
{
    public static boolean isValidString(String value)
    {
        return value != null && !value.isEmpty() && !value.isBlank();
    }
}
