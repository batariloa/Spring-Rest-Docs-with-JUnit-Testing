# Spring-Rest-Docs-with-JUnit-Testing-Demo
Example of generating API documentation with REST Docs, using JUnit tests. 

This project is used for quickstarting REST Docs in my further projects.

## Some important notes

* [The template file ](https://github.com/andrejj10/Spring-Rest-Docs-with-JUnit-Testing-Demo/blob/master/src/main/asciidoc/index.adoc ) (.adoc) formatted must be 
created inside /main/asciidoc manually.

* asciidoctor Maven plugin must be added to the [pom.xml](https://github.com/andrejj10/Spring-Rest-Docs-with-JUnit-Testing-Demo/blob/master/pom.xml) file.

* The snippet path must be configured. For example: <mark> ``` <snippets>${project.build.directory}/generated-snippets</snippets> ```</mark>

*The HTML file will be generated inside the <mark>/target/generated-docs</mark> folder.

## Telling a JUnit test to document the call

Inside the JUnit test, while performing the GET call, we call the method __document("{methodName}})__ to document the request and response with the name of
the test method.

```java
@Test
    public void shouldReturnWeatherToday() throws Exception{
        RequestBuilder request  = MockMvcRequestBuilders.get("/weather/today");
        MvcResult result =   mvc.perform(request)
                 .andExpect(jsonPath("$.id").exists())
                 .andExpect(jsonPath("$.description").exists())
                 .andExpect(jsonPath("$.date").exists())
                 .andDo(document("{methodName}"
                         , preprocessRequest(prettyPrint())
                         ,preprocessResponse(prettyPrint())
                         ,responseFields(
                 fieldWithPath("id").description("The id of the requested object"),
                 fieldWithPath("description").description("Thi is a field for description"),
                fieldWithPath("date").description("This field represents the given date"))))
                .andReturn();


        Assert.assertTrue(result.getResponse().getContentAsString().contains(LocalDate.now().toString())); // we want to assure that the date is correct

    }
```

Packacing the maven project ( call <mark>./mvnw package</mark>) generates the [documentation HTML](https://github.com/andrejj10/Spring-Rest-Docs-with-JUnit-Testing-Demo/blob/master/target/generated-docs/index.html).

