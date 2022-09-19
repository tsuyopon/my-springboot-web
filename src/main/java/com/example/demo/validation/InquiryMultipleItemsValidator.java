package com.example.demo.validation;

import com.example.demo.aop.DemoInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InquiryMultipleItemsValidator implements ConstraintValidator<InquiryMultipleItemsAnnotation, Object> {

    private static Log log = LogFactory.getLog(DemoInvocation.class);

    // バリデーション対象の変数の値を入れる
    private String[] fields;

    // アノテーションクラス（下記2）で設定しているエラーメッセージが入る
    private String message;

    @Override
    public void initialize(InquiryMultipleItemsAnnotation constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        BeanWrapper beanWrapper = new BeanWrapperImpl(value);

        // 入力されている回数をカウントする変数を作成
        Integer idCount = 0;

        for (String field : fields) {
            // 以下で、fieldValueに、validation対象の各値（今回の場合、name1, name2, name3）が入る
            Object getObject = beanWrapper.getPropertyValue(field);


            if (StringUtils.hasText(getObject.toString())) {
                idCount++;
            }
        }

        log.debug("idCount: " + idCount);

        // 入力数が2以上のときはtrueを返す
        if (idCount >= 2) {
            return true;
        }

        // それ以外はfalseを返し、messageを出す
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addPropertyNode(fields[0])
                .addConstraintViolation();
        return false;

    }
}
