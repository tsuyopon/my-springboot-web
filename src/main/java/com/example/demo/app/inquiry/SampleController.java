package com.example.demo.app.inquiry;

import com.example.demo.service.InquiryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Map;

/**
 * Springbootの基本処理を確認するだけのControllerです
 * /sample/testとアクセスすると画面を表示するHello Worldプログラムです。
 */
@Controller
@RequestMapping("/sample")
public class SampleController implements HandlerInterceptor {

    private static Log log = LogFactory.getLog(SampleController.class);

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
        log.debug("GET /sample/test start");
        model.addAttribute("title", "Inquiry Form");
        return "test";  // test.html
    }

    // 特定のメソッドにリクエストが来た際には405 Method Not Allowedとして /sample/testを処理させる
    @RequestMapping(value="/test", method = { RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
    @ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED)
    public String testMethodNotAllowed(@RequestParam(required = false, value="debug", defaultValue="") String debug){

        log.debug("Other than GET /sample/test start");

        // debugパラメータに値があればデバッグログを出力する
        if(debug == null ){ // defaultValueを与える場合にはnullが指定できないのでここには入らない
            log.debug("######## debug parameter is not set");
        } else if (debug == "") {
            log.debug("######## debug parameter is set but empty");
        } else {
            log.debug("######## debug parameter accepted: " + debug);
        }
        return null;  // nullの場合にはempty responseになります。
    }

    //  /sample/select **データが取得できないため、必ずエラーが表示されます**
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


    //  /sample/admin
    @GetMapping("/admin")
    public String admin(Model model){
        log.debug("GET /sample/admin start");
        model.addAttribute("title", "Inquiry Form(Admin)");
        return "test";  // test.html
    }

}
