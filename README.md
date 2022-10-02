# my-springboot-web
Spring Securityのインメモリ認証を試すだけのブランチです。
メモリ上にユーザとパスワード、それに対応するROLEを保持しておきます。

# 対応方法 
- 1. build.gradleに「org.springframework.boot:spring-boot-starter-security」が追加されていること
- 2. WebSecurityConfigurerAdapterを継承したクラスを作成して、@Configurationと@EnableWebSecurityアノテーションを付与して、configureメソッドをオーバーライドすること。以下の3つが用意されています。
  - 全体に対するセキュリティを行うconfigure
  - URLごとにセキュリティを設定するためのconfigure
  - ユーザー・パスフレーズとそれに対応するROLEを定義するconfigure (ここにユーザーとパスワードを定義します)

# 使い方
以下でログインしようとするとログイン画面が表示されます。これはSpring Securityの組み込みのログイン画面です。

- http://localhost:8080/sample/test
  - 「user」、「password」でログインすると「USER」ロールに割り当てられるので閲覧できます。


- http://localhost:8080/sample/admin
  - 「admin」、「password」でログインすると「ADMIN」ロールに割り当てられるので閲覧できます。

なお、ログアウトはSpring Securityで用意されている以下のエンドポイントにアクセスすることで可能です。
- http://localhost:8080/logout
