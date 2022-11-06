package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example.demo")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		// For Debug Only Purpose. Choose 1 Debug Settings
		// see https://docs.oracle.com/javase/6/docs/technotes/guides/security/jsse/JSSERefGuide.html#Debug
//		System.setProperty("javax.net.debug", "ssl");
// 		System.setProperty("javax.net.debug", "ssl:handshake");

		SpringApplication.run(DemoApplication.class, args);
	}

}
