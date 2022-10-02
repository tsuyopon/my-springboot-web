package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        // 静的リソースの場合には認証をかけない
        web.debug(false).ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/users").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .oauth2Login();

    }

    // セッションCookie関連のコードは記述しなくても以下のSet-Cookieを勝手に実施してくれるようだ
    //   Set-Cookie: JSESSIONID=XXXXXXXXXXXXXXXXXX; Path=/; HttpOnly
}
