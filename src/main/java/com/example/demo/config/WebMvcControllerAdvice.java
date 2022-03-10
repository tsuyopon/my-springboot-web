package com.example.demo.config;

import com.example.demo.service.InquiryNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 発生したエラー種類に応じたエラー処理を実装するには、ControllerAdviceアノテーションを適用したクラス内で、
// 引数に例外クラスを指定したExceptionHandlerアノテーションを付与したメソッドを利用することで行える。
@ControllerAdvice
public class WebMvcControllerAdvice {

    // JdbcTemplateのメソッドを使用して、指定したデータが見つからない場合、EmptyResultDataAccessException例外がスローされます。
    @ExceptionHandler (EmptyResultDataAccessException.class)
    public String handlerException(EmptyResultDataAccessException e, Model model){
        model.addAttribute("message", e);
        return "error/CustomPage";
    }

    // 全てのコントローラに対しての例外を補足します。
    //    InquiryControllerにはコントローラ内部でのみ適用される同じ例外ハンドラがあります。(1)
    //    またメソッドの中でのみ例外を捕捉する場合には、InquiryController:update_errorのtry-catchで定義されます(2)
    //    補足の優先順位としては「(2) > (1) > 全体」となります。
    @ExceptionHandler(InquiryNotFoundException.class)
    public String handleException(InquiryNotFoundException e, Model model){
        model.addAttribute("message", e);
        return "error/CustomPage";
    }
}
