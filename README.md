# Spring Security With OAuth2
Spring SecurityでOAuth2を試すブランチです。
このOAuth2では手元で稼働しているdexサーバを使って確認していきます。

# 前提(dexの設定)
dexサーバが下記で起動できるようになっていることが前提です。
- http://192.168.3.19:5556/dex

client_idやsecretは以下に従って生成されていること
- https://github.com/tsuyopon/study/blob/master/dex/setup_grpc_api.md

ただし、192.168.3.19も受け付ける必要があるので証明書発行は以下にしてください。
```
$ export SAN=IP.1:127.0.0.1,IP.2:192.168.3.19
$ ./examples/grpc-client/cert-gen
```

また、config.yamlのissuerは以下のようにしてください。
```
issuer: http://192.168.3.19:5556/dex
```

起動時の設定は下記にしてください。
```
web:
  http://192.168.3.19:5556
```

また、Spring SecurityではコールバックのURIは「/login/oauth2/code/idp」なので下記に指定してください。
```
- id: test1
  redirectURIs:
  - 'http://localhost:8080/login/oauth2/code/idp'
```


# 実装の流れ
- 1. build.gradleの依存パッケージに追加します。
```
	// Spring Seurity
	implementation 'org.springframework.boot:spring-boot-starter-security'

	// OAuth2
	implementation 'org.springframework.security:spring-security-oauth2-client'
	implementation 'org.springframework.security:spring-security-oauth2-jose'
```
- 2. src/main/resources/application.yml
  - ここに指定されたclient_id, client_secretはテスト用です。

- 3. WebSecurityConfigurerAdapterを継承して@Configurationと共に@EnableWebSecurityを指定します。併せて@Overrideでconfigureにより必要な認証を行います。
  - src/main/java/com/example/demo/security/SecurityConfig.java 


- 4. src/main/java/com/example/demo/security/AuthController.java を用意します。
  - 「@AuthenticationPrincipal OAuth2User principal」によりセッションスコープで有効となるユーザー情報を取得できます。


# 試す
- 1. 以下にアクセスします。 
  - http://localhost:8080/auth/user
- 2. 「Log in with Email」を押下して、入力するログイン情報は下記を使用してください。
  - email: admin@example.com
  - password: password
- 3. ログインできれば「{"name":"admin"}」のように表示されます。(adminはSpring Securityから取得したユーザーになります)
- 4. ログイン時にJSESSIONIDのCookieが発行されていることも確認してみてください。
- 5. ログアウトする場合には以下のURLにアクセスします
  - http://localhost:8080/logout

# 特記事項
dexサーバの中でtcpdumpをするとどのエンドポイントにアクセスが来ているかを確認することができます。
```
$ sudo tcpdump port 5555 or port 5556 -A 
```
