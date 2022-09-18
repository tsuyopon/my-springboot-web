# 概要
このブランチでは環境ごとに設定値の参照先(application-xxx.yml)を切り替えたり、環境変数によって値を上書きする方法について取り扱っています

# 基礎知識
「spring.profiles.active」や「SPRING_PROFILES_ACTIVE」でapplication-<env>.ymlのenvの部分を指定します。何も指定しない場合には「default」として扱われます。

以下のようにDspring.profiles.activeとspring.profiles.activeを両方指定した場合には、
コマンドライン引数であるspring.profiles.activeの方が優先となります。
```
$ java -jar -Dspring.profiles.active=local spring-boot-application-properties-sample.jar --spring.profiles.active=local
```

# 詳細
application-local.ymlを作成しているので@Profile("local")を指定した場合に該当のymlファイルの値が@Valueで取得されるようになります。
その中で環境変数から取得するようにしています。なお、環境変数が設定されない場合にはデフォルト値が利用されるようになっています。

以下のエンドポイントを作成しているので、設定値や@Profileなどをいじって挙動を確認することが可能です。
```
/config/test1
```

# 参考資料
- https://spring.pleiades.io/spring-boot/docs/2.1.3.RELEASE/reference/html/boot-features-external-config.html
