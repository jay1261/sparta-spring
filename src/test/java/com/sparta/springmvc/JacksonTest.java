package com.sparta.springmvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springmvc.response.ResponseRestController;
import com.sparta.springmvc.response.Star;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JacksonTest {
    @Test
    @DisplayName("Object to JSON: get Method 필요")
    void test1() throws JsonProcessingException {
        Star star = new Star("star", 10);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(star);

        System.out.println("json = " + json);
    }

    @Test
    @DisplayName("JSON to Object: 기본 생성자 & get or set Method 필요")
    void test2() throws JsonProcessingException {
        String json = "{\"name\": \"Robbie\" , \"age\":95}";
        ObjectMapper objectMapper = new ObjectMapper();

        Star star = objectMapper.readValue(json, Star.class);
        System.out.println("star.getName() = " + star.getName() );
        System.out.println("star.getAge() = " + star.getAge());

    }

}
