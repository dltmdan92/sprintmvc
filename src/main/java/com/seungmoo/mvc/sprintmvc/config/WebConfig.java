package com.seungmoo.mvc.sprintmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 스프링 MVC 설정 확장 --> 보통 이렇게 많이쓴다
@Configuration
// @EnableWebMvc --> 쓰게되면 스프링 MVC 설정을 일일히 직접해줘야한다. 웬만하면 스프링부트에서 그냥 설정된거로 쓰면된다.
public class WebConfig implements WebMvcConfigurer {
    // URL 요청시 /m/ 이렇게 오면 classpath /m 밑의 경로로 정적리소스 매핑하도록
    // WebMvcConfigurer.addResourceHandlers 통해 리소스 매핑 커스텀 할 수 있다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**")
                .addResourceLocations("classpath:/m/")  // 반드시 /로 끝나야함
                .setCachePeriod(20);
    }
}
