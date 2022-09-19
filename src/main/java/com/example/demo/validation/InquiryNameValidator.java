package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// TODO: NameValidatorとしているが空判定しているだけなのでEmptyValidator等の名称でもよかったかもしれない。

// バリデータはConstraintValidator<A,T> を実装する必要があります。Aは制約アノテーション、Tは入力値の型を示します
// Aはinitialize、TはisValidの引数の型となります。
// NameValidatorのアノテーションはField用ですのでConstraintValidatorの<>の2番目の値にはnameの型を表すStringを指定する。
public class InquiryNameValidator implements ConstraintValidator<InquiryNameAnnotation, String> {

    // initializeに渡される第１引数は implements ConstraintValidator<InquiryName, String>で指定された<>内の1つめの型です。
    @Override
    public void initialize(InquiryNameAnnotation constraintAnnotation) {
    }

    // isValidに渡される第１引数は implements ConstraintValidator<InquiryName, String>で指定された<>内の2つめの型です。
    // フィールドアノテーションからこのクラスは呼ばれます。第１引数にはnameが渡されてきます。
    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {

        if( name == ""){
            return false;
        }

        return true;
    }
}