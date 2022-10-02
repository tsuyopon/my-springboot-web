package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/*
 * セキュリティの設定としては、@EnableWebSecurityアノテーションを付与したJava設定クラスを生成する。
 * @EnableWebSecurityは@Configurationと共に付与する必要がある。
 *   cf. https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/config/annotation/web/configuration/EnableWebSecurity.html
 *
 * デフォルトの設定はWebSecurityConfigurerAdapterクラスに書かれているのでこれを継承して、必要な処理があれば上書きをする。
 *
 */
@Configuration
@EnableWebSecurity  //  (1) Spring Securityを使うための設定。@Configurationの箇所に付与する。
public class SecurityConfig extends WebSecurityConfigurerAdapter {  // 継承が必要

    // 以下は全てconfigureメソッドのオーバーライドであることに注意すること。
    // ただし、それぞれで役割が異なる。

    /*
     * パスに対するアクセス許可はconfigure(HttpSecurity)メソッドをオーバーライドして記述する。
     * パスを指定するにはantMatcher(パス)と記述する。
     */
    @Override
    public void configure(WebSecurity web) throws Exception {  // 主に全体に対するセキュリティ設定を行う箇所
        // 認証を無視するエンドポイントを追加することができます。
        //「debug(true)」とすると大量にログ出力してくれてデバッグに便利です。通常はdebug(false)とすれば良いです(コードに含めなくてもOK)
        web.debug(true).ignoring().antMatchers("/css/**","/js/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // 主にURLごとに異なるセキュリティ設定を行う箇所

        // 「/inquiry/*」で始まるものだけは認証不要で許可。それ以外はUSERを持っていれば許可
        // つまり、「/inquoiry/form」などは認証不要だが、「/sample/test」は認証必要です。
        // ログイン画面はSpring Securityで用意されている「/login」が用意されます。ログアウトしたい場合には「/logout」にアクセスします。
        http.authorizeRequests()
                .antMatchers("/inquiry/**").permitAll()
                .antMatchers("/sample/admin").hasRole("ADMIN")
                .anyRequest().hasRole("USER")
                .and()
                    // Possibly more configuration ...
                    .formLogin().permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{  // 主に認証方法の実装の設定を行う。
            // Spring Securityには、メモリ認証があるのでそれを利用する。
            // 以下でユーザとパスフレーズが通った際のroleを定義しています。
            auth
                    // enable in memory based authentication with a user named "user" and "admin"
                    // passwordEncoder(NoOpPasswordEncoder.getInstance())がないとエラーになる。
                    //   (参考) https://www.techiedelight.com/ja/illegalargumentexception-no-passwordencoder-mapped/
                    //.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).
                    .inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).  // FIXME: 本来パスワードはエンコードすべき
                    withUser("user").password("password").roles("USER")  // userでのログインは「USER」ロールを持たせる
                    .and().
                    withUser("admin").password("password").roles("USER", "ADMIN");  // adminでのログインは「USER」「ADMIN」ロールを持たせる。
    }

}
