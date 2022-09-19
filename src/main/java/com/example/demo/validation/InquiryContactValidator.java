package com.example.demo.validation;

import com.example.demo.app.inquiry.InquiryForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InquiryContactValidator implements ConstraintValidator<InquiryContactAnnotation, InquiryForm>  {
    // バリデーションで使用する変数をあらかじめ初期化しておきます。
    @Override
    public void initialize(InquiryContactAnnotation constraintAnnotation) {
    }

    // 第１引数は implements ConstraintValidator<InquiryName, Object>で指定された<>内の２つめの部分です。
    // 相関チェックの場合にはObjectを渡す必要があるらしいです。
    @Override
    public boolean isValid(InquiryForm form, ConstraintValidatorContext context) {

        // 連絡先手段が入力されていない場合にはエラーとする
        if( form.getEmail() == "" && form.getTel() == ""){
            return false;
        }

        return true;
    }
}
