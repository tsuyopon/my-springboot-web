# 概要
このブランチではSpring Retryの実装サンプルを追加しています。
Spring Retryを利用することで本質的なリトライコードのみを記載すればよく実装がシンプルになります。

# 実装手順
基本的には以下の手順に準じています。
- https://www.baeldung.com/spring-retry

手順としては
- 1. RetryConfig.javaを作成して@EnableRetryアノテーションを付与します。なお、このアノテーションは@Configurationに対して有効ですのでこれも指定します。
- 2. retry.propertiesという設定ファイルから設定値を読み込みたいので、RetryConfig.javaにretry.propertiesを利用することを宣言します
- 3. InterfaceクラスとしてRetryService.javaを作成します。
  - @Retryableをリトライしたいメソッドに付与します。どのExceptionを受け取ったらRetryしたいか、何回リトライしたいか、その間隔を指定します。
  - @Recoverをリトライが全て失敗した場合に呼び出したいメソッドに付与します。
- 4. RetryService.javaをimplementsしたRetryServiceImpl.javaを利用します。このメソッドには@Serviceが付与されている必要があります。


# Spring Retryの公式
- https://www.baeldung.com/spring-retry

# 参考資料
- qiita: Spring-Retryでリトライ処理の実装
  - https://qiita.com/SotaOishi/items/f19d50794e3fabad5e95

