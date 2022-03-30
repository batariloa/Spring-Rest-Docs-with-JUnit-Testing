package com.example.demo;

import com.example.demo.entity.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Assert;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * WeatherController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Mar 30, 2022</pre>
 */


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs
public class WeatherControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;


    @Test
    public void shouldReturnWeatherToday() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/weather/today");
        MvcResult result = mvc.perform(request)
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.date").exists())
                .andDo(document("{methodName}"
                        , preprocessRequest(prettyPrint())
                        , preprocessResponse(prettyPrint())
                        , responseFields(
                                fieldWithPath("id").description("The id of the requested object"),
                                fieldWithPath("description").description("Thi is a field for description"),
                                fieldWithPath("date").description("This field represents the given date"))))
                .andReturn();


        Assert.assertTrue(result.getResponse().getContentAsString().contains(LocalDate.now().toString())); // we want to assure that the date is correct

    }

    @Test
    public void shouldReturnWeatherTomorrow() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/weather/tomorrow");
        MvcResult result = mvc.perform(request)
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.date").exists())

                .andDo(document("{methodName}"
                        , preprocessRequest(prettyPrint())
                        , preprocessResponse(prettyPrint())
                        , responseFields(
                                fieldWithPath("id").description("The id of the requested object"),
                                fieldWithPath("description").description("Thi is a field for description"),
                                fieldWithPath("date").description("This field represents the given date"))))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());

        Assert.assertTrue(result.getResponse().getContentAsString().contains(LocalDate.now().plusDays(1).toString())); // we want to assure that the date is correct


    }

} 
