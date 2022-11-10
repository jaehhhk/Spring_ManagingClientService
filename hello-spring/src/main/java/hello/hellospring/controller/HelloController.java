package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //이거 쳐주면 자동으로 임포트
public class HelloController {

    @GetMapping("hello") //hello로 url쳐서 들어오면 이 메서드 실행
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // hello.html로 가서 랜더링 해라

    }

    @GetMapping("hello-mvc")
    // 파라미터 외부에서 받기
    // required 파라미터는 기본이 true이기 때문에 pass, url로 localhost:8080/hello-mvc?name=spring! 형식으로 파라미터 줄 수 있음 -> 파라미터따라 다른 값 노출 가능
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에 body부분에 이 data를 직접 넣어주겠다 -> html 파일, 소스 없이 그대로 해당 파라미터로 넣어준 url대로 띄워줌
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")   // json 형식
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
