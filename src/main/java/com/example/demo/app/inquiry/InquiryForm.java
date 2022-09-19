package com.example.demo.app.inquiry;

import com.example.demo.validation.InquiryContactAnnotation;
import com.example.demo.validation.InquiryMultipleItemsAnnotation;
import com.example.demo.validation.InquiryNameAnnotation;

import javax.validation.constraints.*;

// このクラスはInquiryControllerのメソッドに注入されるクラスです

@InquiryMultipleItemsAnnotation(fields = {"email", "tel"}, message = "email, telの両方に値を指定してください")
@InquiryContactAnnotation
public class InquiryForm {

    @InquiryNameAnnotation
    @Size(min = 1, max=20, message = "Please input less 20 characters")
    private String name;

    @NotNull
    @Email(message = "Invalid E-mail Format")
    private String email;

    @Size(min = 5, max = 15, message = "Please input between 5 and 15 characters")
    @NotEmpty(message = "空です")
    private String tel;

    @NotNull
    private String contents;

    public InquiryForm(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }


    // README.mdに記載していますが、複数の項目をチェックしたい場合には(相関チェックと呼ぶ)、3つの方法があります。
    // そのうちの1つの最もコード量が少ない方法です(ですが、ロジックとデータの分離ができない方法のためお勧めされていない方法です)
    //
    // @AssertTrueを使ってboolean型のメソッドを作成します。
    //   message文言はapplication.properties等から取得可能。 cf: https://www.purin-it.com/spring-boot-web-check-form
    // メソッドの最初には「is」または「get」のGetterメソッドとしておく必要があります
    // 今回はisEmailOrTelExistsとしているので、"emailOrTelExists"というエラー変数名にエラーが入っています。
    // 以下のようにしてエラーを検知可能です。
    //     <p th:if="${#fields.hasErrors('emailOrTelExists')}" th:errors="*{emailOrTelExists}" style="color:red;"></p>
    @AssertTrue(message="EmailかTelのいずれか片方は指定必須です")
    public boolean getEmailOrTelExists() {
        if( email == "" && tel == "" ){
            return false;
        }
        return true;
    }
}
