package com.example.demo.examples.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// 環境毎に呼び出すクラスを分ける実装を見たければ
// com.example.demo.examples.controller.profile配下を参照すること。
@Service
public class MyData {

    @Value("${app.configcontroller.test1_domain}")
    public String test1domain;

    @Value("${app.configcontroller.test2_domain}")
    public String test2domain;

    @Value("${app.configcontroller.test3_domain}")
    public String test3domain;

}
