package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        /**
         * templates/home.html
         * static에 만든 녀석(index.html)은? -> 매핑된게 먼저 있으면 컨트롤러에서 호출, 없으면 index.html
         */
        return "home";
    }


}
