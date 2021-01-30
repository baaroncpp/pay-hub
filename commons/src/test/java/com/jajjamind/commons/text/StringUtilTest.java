package com.jajjamind.commons.text;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author akena
 * 23/11/2020
 * 15:23
 **/
//@ExtendWith(MockitoExtension.class)
public class StringUtilTest {


    @Test
    public void testThatCanValidateEmptyString(){

        String testString = "";
        boolean check = StringUtil.isEmpty(testString);

        Assertions.assertThat(check)
                .isTrue();

        testString = "123";
        check = StringUtil.isEmpty(testString);

        Assertions.assertThat(check)
                .isFalse();

    }
}
