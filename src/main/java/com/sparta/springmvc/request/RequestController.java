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

    //POST http://localhost:8080/hello/request/form/model
    // Body  @ModelAttribute를 사용하면 Body에 들어온 쿼리스트링 방식 데이터를 객체에 매핑해서 가져올 수 있다.
    // name=Robbie&age=95
    @PostMapping("form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {
        return String.format("Hello, @RequestParam. <br> name = %s, age = %d", star.name, star.age);
    }

    //GET http://localhost:8080/hello/request/form/param/model?name=Robbie&age=95
    @GetMapping("/form/param/model")
    @ResponseBody
    // @ModelAttribute 생략 가능
    public String helloRequestParam(/*@ModelAttribute*/ Star star) {
        return String.format("Hello, @RequestParam. <br> name = %s, age = %d", star.name, star.age);
    }

    //POST http://localhost:8080/hello/request/form/json
    // Body
    // {"name":"Robbie", "age":95}
    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        return String.format("Hello, @RequestParam. <br> name = %s, age = %d", star.name, star.age);
    }
}
