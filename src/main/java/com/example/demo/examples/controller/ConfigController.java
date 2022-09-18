package com.example.demo.examples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 「SPRING_PROFILES_ACTIVE」や「spring.profiles.active」が何も指定されない場合には「default」となります。
// 「local」などの他の値を指定すると「default」と記述しないと認識させることができないので要注意です。
@Profile({"default", "local"})
@RestController
public class ConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private MyData myData;

    @RequestMapping("/config/test1")
    public String test1() {
        StringBuilder sb = new StringBuilder();
        sb.append("test1_config: ");
        sb.append(myData.test1domain);
        sb.append("</br>\n");
        sb.append("test2_config: ");
        sb.append(myData.test2domain);
        sb.append("</br>\n");
        sb.append("test3_config: ");
        sb.append(myData.test3domain);
        sb.append("</br>\n");
        logger.info("/config/test1 start");
        return sb.toString();
    }

}
