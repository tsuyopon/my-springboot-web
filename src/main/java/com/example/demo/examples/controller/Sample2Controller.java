package com.example.demo.examples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * DIコンテナで管理するクラスに@ConfigurationPropertiesを付与すると、@Valueアノテーション不要で
 * クラス内のプロパティに設定値（「prefix属性に指定したプレフィック + "." + プロパティ名」の値）が
 * 自動的にインジェクションされます。
 * デフォルト値を設けたい場合は、プロパティの初期値として指定してください。
 */
@RestController
@RequestMapping("/examples")
@ConfigurationProperties("app")
public class Sample2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Sample2Controller.class);

    // 注入される変数の定義
    private String name;
    private String version;

    // 以下はapplication.ymlに存在しないのでデフォルト値が使われる
    private String none = "None String";

    // @ConfigurationPropertiesが指定されていても、
    // Setter, Getterの両方を記述する必要があります。
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNone() {
        return none;
    }

    public void setNone(String none) {
        this.none = none;
    }

    // /examples/test2
    @GetMapping("/test2")
    public String test2() {
        logger.info("app-name = {}:{}:{}", this.name, this.version, this.none);
        return "application and version";
    }
}
