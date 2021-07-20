package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // Static 테스트

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";     // resources/templates/hello.html로 이동
    }

    // Model, View 테스트
    // http://localhost:8080/hello-mvc?name=spring
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody  //view 이런거 없이 그대로 출력(API에는 필수)
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 문자열인경우 default: stringconverter
    }

    // API 테스트
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // json 형태로 전달됨: http://localhost:8080/hello-api?name=Bob?
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 객체인경우 default: jsonconverter
    }
}
