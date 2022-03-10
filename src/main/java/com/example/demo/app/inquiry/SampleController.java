package com.example.demo.app.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Springbootの基本処理を確認するだけのControllerです
 * /sample/testとアクセスすると画面を表示するHello Worldプログラムです。
 */
@Controller
@RequestMapping("/sample")
public class SampleController {

    //  /sample/test
    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("title", "Inquiry Form");
        return "test";  // test.html
    }
}
