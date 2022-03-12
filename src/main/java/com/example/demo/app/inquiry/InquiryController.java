package com.example.demo.app.inquiry;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryNotFoundException;
import com.example.demo.service.InquiryService;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService){
        this.inquiryService = inquiryService;
    }

    // /inquiry/index(GET)
    // お問い合わせ一覧ページ
    @GetMapping("/index")
    public String index(Model model){
        List<Inquiry> list = inquiryService.getAll();

        model.addAttribute("inquiryList", list);
        model.addAttribute("title", "inquiryIndex");

        return "inquiry/index";
    }

    // /inquiry/index_boot(GET)
    // お問い合わせ一覧ページ(/indexをbootstrapで表示したもの)
    @GetMapping("/index_boot")
    public String index_boot(Model model){
        List<Inquiry> list = inquiryService.getAll();

        model.addAttribute("inquiryList", list);
        model.addAttribute("title", "inquiryIndex");

        return "inquiry/index_boot";
    }

    // /inquery/form (GET)
    @GetMapping("/form")
    public String form(InquiryForm inquiryForm,
                       Model model,
                       @ModelAttribute("complete") String complete    // フラッシュスコープの値をレンダリングできるようにできる
                       ){
        model.addAttribute("title", "Inquiry Form");
        return "inquiry/form";  // inquiry/form.html
    }

    // /inquery/form (POST)
    @PostMapping("/form")
    public String formGoBack(InquiryForm inquiryForm, Model model){
        model.addAttribute("title", "Inquiry Form");
        return "inquiry/form"; // inquiry/form.html
    }

    // /inquery/confirm (POST)
    @PostMapping("/confirm")
    public String confirm(@Validated InquiryForm inquiryForm,  // @Validatedでバリデーションがかかるようになる
                          BindingResult result,
                          Model model) {
        if(result.hasErrors()){
            model.addAttribute("title", "Inquiry Form");
            return "inquiry/form"; // inquiry/form.html
        }
        model.addAttribute("title", "Confirm Page");
        return "inquiry/confirm"; // inquiry/confirm.html
    }

    @PostMapping("/complete")
    public String complete(@Validated InquiryForm inquiryForm,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes   // フラッシュスコープを利用させる
                           ){

        if(result.hasErrors()){
            model.addAttribute("title", "Inquiry Form");
            return "inquiry/form";
        }

        // お問い合わせに保存を行う処理
        Inquiry inquiry = new Inquiry();
        inquiry.setName(inquiryForm.getName());
        inquiry.setEmail(inquiryForm.getEmail());
        inquiry.setContents(inquiryForm.getContents());
        inquiry.setCreated(LocalDateTime.now());
        inquiryService.save(inquiry);

        redirectAttributes.addFlashAttribute("complete", "Registered");  // "Registered"はサーバ側で保持
        return "redirect:/inquiry/form";
    }


    //  /inquiry/update_error
    //
    //  強制的に更新エラーを引き起こさせるためのエンドポイントです。
    //  メソッドの中で独自で定義した例外(InquiryNotFoundException)を捕捉する
    @GetMapping("/update_error")
    public String update_error(Model model){

        Inquiry inquiry = new Inquiry();
        inquiry.setId(4);
        inquiry.setName("hoge4");
        inquiry.setEmail("sample4@sample.com");
        inquiry.setContents("Hello4");

        try {
            inquiryService.update((inquiry));
        } catch (InquiryNotFoundException e) {
            // (*1)よりもこちらの補足が優先される
            model.addAttribute("message", e);
            return "error/CustomPage";
        }

        return "test";  // test.html
    }

    // このInquiryControllerコントローラ内部での例外補足を行う (*1)
    @ExceptionHandler(InquiryNotFoundException.class)
    public String handleException(InquiryNotFoundException e, Model model){
        model.addAttribute("message", e);
        return "error/CustomPage";
    }

}
