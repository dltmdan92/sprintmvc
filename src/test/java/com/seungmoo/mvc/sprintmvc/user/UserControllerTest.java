package com.seungmoo.mvc.sprintmvc.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void createUser_JSON() throws Exception {
        String userJson = "{\"username\": \"seungmoo\", \"password\":\"1234\"}";

        // 스프링 ViewResolver에서 헤더 accept 부분을 보고 어떤 타입의 response를 원하는지 체크, response 안맞으면 HttpMediaTypeNotAcceptableException 발생
        // HttpMessageConvertersAutoConfiguration --> 스프링부트 메시지 컨버터
        // accept에서 XML으로 받고 싶으면 XML 메시지 컨버터를 classpath에 등록해줘야함(pom.xml), JSON은 기본 제공이다.
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON) // request는 json으로 보내고
                .accept(MediaType.APPLICATION_XML) // response는 XML로 받기
                .content(userJson)) // 여기까지 요청을 만드는 단계, 아래부터 응답을 받는 부분임
                .andExpect(status().isOk())
                .andExpect(xpath("/User/username").string("seungmoo"))
                .andExpect(xpath("/User/password").string("1234"));
                //.andExpect(jsonPath("$.username", is(equalTo("seungmoo"))))
                //.andExpect(jsonPath("$.password", is(equalTo("1234"))));
    }
}
