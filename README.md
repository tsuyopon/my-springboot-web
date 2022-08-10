# my-springboot-web
My Spring Boot Web Application Sample for exercise


このレポジトリは以下から生成したものに対して修正を加えています。
- https://start.spring.io/

上記サイトの2022/03/06デフォルト値として指定されている以下の値が選択されています。
- Project: Gradle
- Lauguage: Java
- Spring Boot: 2.6.4
- Project Metadata
  - Group: com.example
  - Artifact: demo
  - Name: demo
  - Description: Demo project for Spring Boot
  - Package name: com.example.demo
  - Packaging: Jar
  - Java: 11

# 起動方法
以下で起動することができます。
```
$ gradle bootRun
```

# エントリポイント
デフォルトはhttp://localhost:8080/で稼働します。

## HelloWorld
- サンプル: /sample/test
- 強制的に例外エラーを発生させる: /sample/select

## お問い合わせ
- お問い合わせ画面: /inquiry/form
- 入力内容確認画面: /inquiry/confirm
- 完了エントリポイント(画面はなく登録完了後にリダイレクト): /inquiry/complete
- お問い合わせ一覧画面:
  - /inquiry/index
  - /inquiry/index_boot (Bootstrapライブラリを利用したバージョン。機能は/inquiry/indexと同じ)

強制的にSQL更新エラーを引き起こしてカスタム例外を捕捉するエントリポイントの追加
- /inquiry/update_error

AOPのライブラリの追加
- DemoInvocation.java: メソッドの実行前後に共通でログを出力させるようにする

## サンプル系
- 外部設定読み込み
  - @Valuesの利用: /examples/test1
  - @ConfigurationPropertiesの利用: /examples/test2
- @Profileによるメソッドの環境別出し訳サンプル: /examples/profile


# 機能
- main(@SpringBootApplication), Controller(@Controller), Service(@Service), Dao(@Repository)の利用
  - controller => service => dao => entity(O/Rマッピング)
- H2 Databaseへの機能 
  - src/main/resources/のdata.sql, schema.sqlは自動的に読み込まれます。data.sqlは自動的にデータベースにSQLが取り込まれています。
- 例外の補足
  - RuntimeExceptioを継承したカスタムExceptionの定義
  - メソッド内例外捕捉、コントローラ内例外捕捉、全コントローラ内例外補足
- 各マッピングの前後で共通処理機能のInterceptor
