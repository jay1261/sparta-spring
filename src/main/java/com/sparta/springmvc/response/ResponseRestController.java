package com.sparta.springmvc.response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController = Controller + ResponseBody
// html을 반환할 일이 없다면 RestController를 사용
@RestController
@RequestMapping("/response/rest")
public class ResponseRestController {
    @GetMapping("/json/string")
    public String helloStringJson() {
        return "{\"name\": \"Robbie\" , \"age\":95}";
    }

    @GetMapping("/json/class")
    public Star helloClassJson(){
        return new Star("star", 10);
    }
}
