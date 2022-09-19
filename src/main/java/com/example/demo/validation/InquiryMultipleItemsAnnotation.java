package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/*
 * @InquiryMultipleItems アノテーション
 * fieldsに2つ以上のクラス変数を指定します。それらの要素が入力値として2つ以上指定されていなければエラーを出力します。
 * cf. https://qiita.com/daifuku_mochi2/items/c90cab977f0f37b30dbf
 */

@Documented
// 1で記載した入力チェック内容を記載したクラスをかく
@Constraint(validatedBy = {InquiryMultipleItemsValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InquiryMultipleItemsAnnotation {

    // 入力チェック不可だった場合に、表示するエラーメッセージ
    String message() default "name1, name2, name3のいずれか2つ以上を入力してください。";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        // 型はクラス名と合わせる
        InquiryMultipleItemsAnnotation[] value();
    }

    // アノテーションの指定からの値 ( 例: fields = {"email", "tel"} の値を格納する )
    String[] fields();
}
