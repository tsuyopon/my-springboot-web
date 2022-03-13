package com.example.demo.examples.controller.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// spring.profiles.active=developmentの場合に呼ばれます
@Component
@Profile("development")
public class DevelopmentProfile implements ProfileSample {
    public String getValue() {
        return "This is development";
    }
}
