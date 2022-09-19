package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * Nameへのカスタムバリデーションです (FIELD用アノテーションです)
 */

// これを付けておくと、そのアノテーションは Javadoc API ドキュメントの出力にも表示されるようになる。
@Documented
// 以下でこのアノテーションによってバリデーションとして利用されるクラスを指定する
@Constraint(validatedBy = {InquiryNameValidator.class})
// どのタイプの要素（クラスやメソッドなど）に対してアノテーションを付加できるかを制限できる。
// 相関チェックの場合には複数にまたがるので項目に対してアノテーションをつけることはできません。
@Target({FIELD})
// アノテーション情報をどの段階まで保持するかを制御できる(SOURCE, CLASS, RUNTIMEが指定できる)
@Retention(RetentionPolicy.RUNTIME)
// @interfaceはアノテーションの宣言となる。
public @interface InquiryNameAnnotation {

    // 入力チェック不可だった場合に、表示するエラーメッセージ
    // FIELDアノテーションだとおそらく、fields.globalErrors()ではなくfields.allErrors()側で表示される。
    String message() default "名前が入力されていません。";

    // groups は、状況に応じて制約チェックの実行の是非を判別させるための属性を指定します。groups属性を指定すると、制約を任意のグループにまとめることができ、バリデーションの実行時にそのグループを指定することで、グループごとに異なる制約のチェックを行うことができます。
    // 今回はグループにまとめる必要がないため、空で実装しています。
    Class<?>[] groups() default {};

    // payload は、制約違反に対して重要度などの任意のカテゴリを付与する属性を指定します。
    // 必要に応じて使用しますが、今回は特に任意のカテゴリはないので、空で実装しています。
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    // チェックする値を格納する。value()の型はこのアノテーションと同一にするものっぽい
    public @interface List {
        InquiryNameAnnotation[] value();
    }
}
