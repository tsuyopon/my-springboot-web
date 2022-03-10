package com.example.demo.app.inquiry;

import com.example.demo.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Springbootの基本処理を確認するだけのControllerです
 * /sample/testとアクセスすると画面を表示するHello Worldプログラムです。
 */
@Controller
@RequestMapping("/sample")
public class SampleController {

    private final JdbcTemplate jdbcTemplate;
    private final InquiryService inquiryService;

    @Autowired
    public SampleController(JdbcTemplate jdbcTemplate, InquiryService inquiryService) {
        this.jdbcTemplate = jdbcTemplate;
        this.inquiryService = inquiryService;
    }

    //  /sample/test
    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("title", "Inquiry Form");
        return "test";  // test.html
    }

    //  /sample/select
    //
    //  データが取得できずに例外が飛ぶために、その後WebMvcControllerAdviceの例外補足で処理される
    @GetMapping("/select")
    public String select(Model model){

        // data.sqlには3件のSQLが挿入されるので、あえてエラーとするようにid=4を指定している。
        String sql = "SELECT id, name, email FROM inquiry WHERE id = 4";
        Map<String,Object> map = jdbcTemplate.queryForMap(sql);

        model.addAttribute("title", "Inquiry form");
        model.addAttribute("name", map.get("name"));
        model.addAttribute("email", map.get("email"));
        return "select"; // select.html
    }

}
