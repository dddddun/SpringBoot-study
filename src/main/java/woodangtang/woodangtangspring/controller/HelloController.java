package woodangtang.woodangtangspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    /** Controllerl를 사용한 간단한 페이지 출력 **/
    // 웹 어플리케이션에서 /hello 로 들어오면 아래의 메서드를 호출해준다.(스프링이 해줌)
   @GetMapping("hello")
    // 스프링이 모델을 만들어서 넣어줌
    public  String hello(Model model) {
       // attributeName: 과 attributeValue는 인텔리제이가 파라미터 이름을 편리하게 보여주는 기능이다
       // medel.addAttribute("data", "hello!!");라고 입력하면 된다.
       // 모델에 키:data, 값:hello!!를 담는다
       model.addAttribute("data", "hello!!");
       // resources>templates>hello로 return한다. (Thymeleaf 템플릿 엔진 처리)
       // 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다.
       // 스프링 부트 템플릿 엔진 기본 viewName 매핑
       // `resources:templates/` + {viewName) + `.html`
       return "hello";
       // 참고) `spring-boot-devtools`라이브러리를 추가하면 `html`파일을 컴파일만 해주면 서버 재시작 없이 View파일 변경 가능
    }


    /** get방식으로 받은 데이터로 페이지를 출력 **/
    @GetMapping("hello-mvc")
    // 외부에서 파라미터를 받아온다.
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
       // 파라미터로 넘어온 name을 모델에 담는다.
       model.addAttribute("name", name);
       // hello-template으로 모델을 넘겨준다.
       return "hello-template";
    }


    /** API (String 전달) **/
    @GetMapping("hello-string")
    // http 에서 헤더부와 바디부 중 바디부에 다음 데이터를 직접 넣어주겠다.
    // 이게 없으면 viewResolver로 넘겨 알맞는 템플릿으로 보낸다.
    // 즉, @ResponseBody가 있는 경우 HttpMessageConverter가 작동하는데
    // 그냥 단순 문자일 경우  StringConverter가 작동하고 (StringHttpMessageConverter)
    // 객체일 경우 JsonConverter가 작동한다. (MappingJackson2HttpMessageConverter)
    // 위의 컨버터들은 라이브러리다.
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
       // 단순 문자를 보내기 때문에 StringConverter가 작동하여 문자 그대로 요청한곳으로 보내짐
       return "hello " + name;
    }

    /** API (객체 전달) **/
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        // 문자가 아닌 객체를 보내기 때문에 JsonConverter가 작동하여 그 결과
        // SON방식({"키":"값"}형태)로 보내짐
        return hello;
   }

    // static class로 만들면 HelloController Hello 클래스를 쓸 수 있다.
    static class Hello {
        private  String name;

        // 자바빈 규약, 프라이빗이라 접근할 수 없는 데이터에 메서드를 통해 접근
        // 프로퍼티 접근 방식이라고도 한다.
       public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

