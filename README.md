# my-springboot-web
複数の項目を同時にチェックしてバリデーションを行う方法です(相関チェック又は相関バリデーションと呼ばれます)。

この方法に記載された３つの方法があります。
- https://penguinlabo.hatenablog.com/entry/springboot/web/7_correlationvalidation


- 方法1 - フォームBeanに検証メソッドを実装
  - 実装量が非常に少ないが、1つのフォームに複数のバリデーションを実装すると、Beanのコードがカオスな状態となるようです。(上記資料ではお勧めされていません)
- 方法2 - バリデーションアノテーションを自作
- 方法3 - Springバリデーションを実装


# 方法
詳細はコードを見てみてください。

## 実装方法1
- @AssertTrueアノテーションをメソッドに指定する。
- メソッドは、存在しないフィールドのgetterメソッドとして実装する。
- getterメソッドなので、メソッド名は「is」もしくは「get」で始まる必要がある。
- テンプレート側では「is」や「get」を抜いて、先頭文字を小文字として参照できる。isTestMethodの場合はtestMethodで参照可能
- 戻り値の型はbooleanにする。バリデーションの結果、正当であればtrue、不当であればfalseを復帰値とする。

この実装は
フォームBeanにバリデーションロジックが入り込むということで、定義と実装が疎結合にならない部分が難点となります。
この難点を回避するには実装方法2のバリデーションのアノテーションを自作する必要があります

なお、InquiryForm.javaのgetEmailOrTelExistsメソッドにはこの実装が存在します。

## 実装方法2

独自アノテーションを追加しています。
InquiryForm.javaに指定されている下記のアノテーションは独自アノテーションです。
- @InquiryContactAnnotation
  - クラスに指定するとemailかtelのどちらかが設定されているかどうかをチェックするTYPEアノテーション
- @InquiryNameAnnotation
  - フィールドに指定すると空かどうかをチェックするだけのFIELDアノテーション
- @InquiryMultipleItemsAnnotation
  - アノテーションから指定されたフィールドの要素が２つ以上入力されているかどうかをチェックするTYPEアノテーション

これらのカスタムバリデーションは以下に実装が存在しています。
- com.example.demo.validation


## 実装方法3 
TBD (今回はまだ実装を含めていません)


