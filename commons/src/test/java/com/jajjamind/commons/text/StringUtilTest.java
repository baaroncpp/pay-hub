package com.jajjamind.commons.text;

import org.assertj.core.api.AssertionErrorCollector;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author akena
 * 23/11/2020
 * 15:23
 **/
@RunWith(MockitoJUnitRunner.class)
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
