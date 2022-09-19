package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/*
 * 連絡先(EmailとTelの相関チェック)へのカスタムバリデーションです。
 * 複数のフィールドのチェックとなるので、TYPE(Classやenum)等に付与するアノテーションとして実装しています。
 */

@Documented
@Constraint(validatedBy = {InquiryContactValidator.class})
// emailとtelと複数のフィールドである必要があるので、クラスに付与するTYPEとする必要がある。
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InquiryContactAnnotation {

    // 入力チェック不可だった場合に、表示するエラーメッセージ
    String message() default "連絡先手段(EmailまたはTel)が入力されていません。";

    // groups は、状況に応じて制約チェックの実行の是非を判別させるための属性を指定します。groups属性を指定すると、制約を任意のグループにまとめることができ、バリデーションの実行時にそのグループを指定することで、グループごとに異なる制約のチェックを行うことができます。
    // 今回はグループにまとめる必要がないため、空で実装しています。
    Class<?>[] groups() default {};

    // payload は、制約違反に対して重要度などの任意のカテゴリを付与する属性を指定します。
    // 必要に応じて使用しますが、今回は特に任意のカテゴリはないので、空で実装しています。
    Class<? extends Payload>[] payload() default {};

//    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
//    @Retention(RUNTIME)
//    @Documented
//    // チェックする値を格納する。value()の型はこのアノテーションと同一にするものっぽい
//    public @interface List {
//        InquiryContact[] value();
//    }

}
