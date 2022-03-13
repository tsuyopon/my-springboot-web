package com.example.demo.examples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/examples")
public class Sample1Controller {

        private static final Logger logger = LoggerFactory.getLogger(Sample1Controller.class);

        /*
         * @Valueアノテーションによりapplication.ymlやapplication.propertiesに設定された内容を取得して注入できる。
         * see: https://qiita.com/cfg17771855/items/905da3100ae99c5197f0
         */
        @Value("${app.sample1controller.name}")
        private String applicationName;

        @Value("${app.sample1controller.version}")
        private String applicationVersion;

        // 環境変数をそのまま指定して注入することもできるようです。
        @Value("${JAVA_HOME}")
        private String javaHome;

        // /examples/test1
        @GetMapping("/test1")
        public String test1() {
            logger.info("app-name = {}:{}, javahome={}", this.applicationName, this.applicationVersion, this.javaHome);
            return "test";
        }
}
