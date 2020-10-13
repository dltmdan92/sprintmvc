package com.seungmoo.mvc.sprintmvc.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // HttpMessageConverters
    // ex) @RequestBody @ResponseBody
    // HTTP request 본문을 객체로 변경하거나, 객체를 HTTP response본문으로 변경할 때 사용
    // RequestBody의 경우 Content-Type에 따라 사용되는 Converter가 다름 (Content-Type : json --> JsonMessageConverter)
    // ResponseBody의 경우 객체가 리턴될 경우(컴포지션 타입) 일반적으로 JsonMessageConverter, String이나 int가 리턴될 경우 StringMessageConverter 
    // @RestController가 선언되어있을 경우 @ResponseBody는 생략가능(@Controller의 경우 @ResponseBody가 자동적용되지 않으며, ViewNameResolver가 적용될 것임)
    @PostMapping("/users/create")
    public @ResponseBody User create(@RequestBody User user) {
        return user;
    }

}
