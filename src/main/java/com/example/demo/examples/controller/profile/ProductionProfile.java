package com.example.demo.examples.controller.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// spring.profiles.active=productionの場合に呼ばれます
@Component
@Profile({"production", "prod"})
public class ProductionProfile implements ProfileSample {
    public String getValue() {
        return "This is production";
    }
}
