package com.jitsu.api.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.assertj.core.util.Strings;
import org.junit.Assert;
import com.mysql.cj.util.StringUtils;

import java.io.IOException;
import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class BaseSteps {

    public void verifyResponseStatus(Response rest, int expected) {
        int actual = rest.getStatusCode();
        System.out.println("Verify response status: actual is " + actual + ", expected is " + expected);
        assertThat(actual).isEqualTo(expected);
    }

    public void verifyResponseStatusNotEqual(Response rest, int expected) {
        int actual = rest.getStatusCode();
        System.out.println("Verify response status: actual is " + actual + ", expected is " + expected);
        assertThat(actual).isNotEqualTo(expected);
    }

    public void verifyResponseStatusNotEqual(String message, String actual, String expected) {
        System.out.println(message + " actual is " + actual + ", expected is " + expected);
        assertThat(actual).isNotEqualTo(expected);
    }

    @Step
    public void verifyResponseValueEqual(String message, String actual, String expected) {
        System.out.println(message + " actual is " + actual + ", expected is " + expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Step
    public void verifyResponseValueEqual(String message, boolean actual, boolean expected) {
        System.out.println(message + " actual is " + actual + ", expected is " + expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Step
    public void verifyResponseValueNotNullOrEmpty(String message, String actual) {
        System.out.println(message + " actual is " + actual);
        assertThat(Strings.isNullOrEmpty(actual)).isEqualTo(false);
    }

    @Step
    public void verifyResponseValueNullOrEmpty(String message, String actual) {
        System.out.println(message + " actual is " + actual);
        assertThat(Strings.isNullOrEmpty(actual)).isEqualTo(true);
    }

    @Step
    public void verifyResponseValueContain(String message, String actual, String expected) {
        System.out.println(message + " actual is " + actual + ". Expected is " + expected);
        assertThat(actual).contains(expected);
    }

    @Step
    public void verifyResponseValueContainInList(String message, String expected, List<String> actual) {
        System.out.println(message + " actual is " + actual + ". Expected is " + expected);
        assertThat(actual).contains(expected);
    }

    @Step
    public void verifyResponseValueInList(String message, List<String> expected, List<String> actual) {
        System.out.println(message + " actual is " + actual + ". Expected is " + expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Step
    public void verifyResponseValueStartWith(String message, String actual, String expected) {
        System.out.println(message + " actual is " + actual + ". Expected is " + expected);
        assertThat(actual).startsWith(expected);
    }


    @Step
    public void verifyResponseValueNotEqual(String message, String actual, String expected) {
        System.out.println(message + " actual is " + actual + ", expected is " + expected);
        assertThat(actual).isNotEqualTo(expected);
    }

    @Step
    public String getResponseValueByKey(Response response, String keyPath) {
        try {
            if (response.contentType().contains("json")) {
                return response.jsonPath().get(keyPath) + "";
//                return response.getBody().
            } else
                return response.xmlPath().get(keyPath) + "";
        } catch (Exception e) {
            throw new RuntimeException("Cannot find value with key path: " + keyPath + "\n" + e.getMessage());
        }
    }


    @Step
    public void verifyErrorResponseStatus(Response rest) {
        int actual = rest.getStatusCode();
        System.out.println("Response status: actual is " + actual);
        assertThat(actual).isIn(400, 403, 404, 500);
    }

    @Step
    public void verifyIsMatchExpectedListData(List<String> listActual, List<String> listExpected) {
        boolean result = true;
        Assert.assertEquals("Actual size is: " + listActual.size() + " .Expected size is: " + listExpected.size(),
                listExpected.size(), listActual.size());
        for (String actual : listActual) {
            if (!listExpected.contains(actual)) result = false;
        }
        assertTrue("Actual is: " + listActual + " .Expected is: " + listExpected, result);
    }

    public static String modifyJson(String jsonBody, String[] jsonPaths, String... values) {
        Configuration configuration = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
        DocumentContext parsed = JsonPath.using(configuration).parse(jsonBody);
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < jsonPaths.length; i++) {
            if (!StringUtils.isNullOrEmpty(jsonPaths[i])) {
                switch (values[i]) {
                    case "{DELETE}":
                        parsed.delete(jsonPaths[i]);
                        break;
                    case "null":
                        parsed.set(jsonPaths[i], null);
                        break;
                    case "\"\"":
                        parsed.set(jsonPaths[i], "");
                        break;
                    case "true":
                    case "false":
                        parsed.set(jsonPaths[i], Boolean.parseBoolean(values[i]));
                        break;
                    default:
                        // Check if the value is an array
                        if (values[i].startsWith("[") && values[i].endsWith("]")) {
                            // Convert the string to a list and set it in the JSON
                            List arrayValue = null;
                            try {
                                arrayValue = mapper.readValue(values[i], List.class);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            parsed.set(jsonPaths[i], arrayValue);
                        } else {
                            parsed.set(jsonPaths[i], values[i]);
                        }
                }
            }
        }
        return parsed.jsonString();
    }


}