package com.example.demo.examples.controller.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// spring.profiles.activeが何も指定されない場合には@Profile("default")が呼ばれます
@Component
@Profile("default")
public class DefaultProfile implements ProfileSample {

    public String getValue() {
        return "This is default";
    }

}