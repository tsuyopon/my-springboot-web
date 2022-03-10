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


# エントリポイント
デフォルトはhttp://localhost:8080/で稼働します。

## HelloWorld
- サンプル: /sample/test
- 強制的に例外エラーを発生させる: /sample/select

## お問い合わせ
- お問い合わせ画面: /inquiry/form
- 入力内容確認画面: /inquiry/confirm
- 完了エントリポイント(画面はなく登録完了後にリダイレクト): /inquiry/complete
- お問い合わせ画面: /inquiry/form


# 機能
- main(@SpringBootApplication), Controller(@Controller), Service(@Service), Dao(@Repository)の利用
  - controller => service => dao => entity(O/Rマッピング)
- H2 Databaseへの機能 
  - src/main/resources/のdata.sql, schema.sqlは自動的に読み込まれます。data.sqlは自動的にデータベースにSQLが取り込まれています。
- 例外の補足
