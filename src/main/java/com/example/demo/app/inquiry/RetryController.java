package com.example.demo.app.inquiry;

import com.example.demo.service.RetryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/retry")
public class RetryController {
    private static Log log = LogFactory.getLog(RetryController.class);

    private final RetryService retryService;

    @Autowired
    public RetryController(RetryService retryService){
        this.retryService = retryService;
    }

    //  /retry/test1
    @GetMapping("/test1")
    public String test1(Model model) throws Exception {
        log.debug("GET /retry/test1 start");
        retryService.myRetryService1("testparam");
        model.addAttribute("title", "Retryableテスト");
        return "test";  // test.html
    }

    //  /retry/test2
    @GetMapping("/test2")
    public String test2(Model model) throws Exception {
        log.debug("GET /retry/test2 start");
        retryService.myRetryService2("testparam");
        model.addAttribute("title", "Retryableテスト(設定値読み込み)");
        return "test";  // test.html
    }
}