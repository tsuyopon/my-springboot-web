package com.example.demo.examples.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * spring.profiles.activeの値がdevelopmentかproductionで、同一メソッドだけどその処理を分けたい場合があります。
 * このようなケースでは interfaceを用意して、
 * see: https://qiita.com/kagamihoge/items/c2146fd61b4ee19cc85a
 */
@RestController
@RequestMapping("/examples")
public class ProfileController {

    @Autowired
    ProfileSample profileSample;

    // /examples/profile
    @GetMapping("/profile")
    public String profile() {
        // interfaceクラスのメソッドを呼び出します。
        // spring.profiles.activeが設定されていなければ、DefaultProfie.java
        // spring.profiles.active=developmentが設定されていれば、DevelopmentProfile.java
        // spring.profiles.active=prod,productionいずれかが設定されていれば、ProductionProfile.java
        // が呼ばれます。
        return profileSample.getValue();
    }
}
