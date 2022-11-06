package com.example.demo.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TopController {

    private static Log log = LogFactory.getLog(TopController.class);

    //  ポータルトップ用のエンドポイント
    @GetMapping("/")
    public String test(Model model){
        log.debug("GET / start");
        model.addAttribute("ポータルトップ", "Inquiry Form");
        return "index";  // test.html
    }

}
