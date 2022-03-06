package com.example.demo.app.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleController {

        @GetMapping("/test")
        public String test(Model model){
            model.addAttribute("title", "Inquiry Form");
            return "test";  // test.html
        }
}
