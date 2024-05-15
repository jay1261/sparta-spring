package com.sparta.springmvc.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class RequestController {

    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

    // http://localhost:8080/hello/request/star/robbie/age/90
    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody
    public String helloRequestPath(@PathVariable(required = true) String name, @PathVariable int age) {
        return String.format("Hello, @PatVariable.<br> name = %s, age = %d", name, age);
    }

    // http://localhost:8080/hello/request/form/param?name=Robbie&age=95
    @GetMapping("/form/param")
    @ResponseBody
    // @RequestParam 생략 가능
    public String helloGetRequestParam(@RequestParam(required = false) String name, /*@RequestParam*/ int age) {
        return String.format("Hello, @RequestParam. <br> name = %s, age = %d", name, age);
    }


    // http://localhost:8080/hello/request/form/param
    // 주소에 데이터가 있는게 아니고 Body에 있다
    // name=Robbie&age=95
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam. <br> name = %s, age = %d", name, age);
    }
}
