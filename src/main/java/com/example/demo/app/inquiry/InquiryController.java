package com.example.demo.app.inquiry;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryService;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // お問い合わせ一覧ページ
    @GetMapping
    public String index(Model model){
        List<Inquiry> list = inquiryService.getAll();

        model.addAttribute("inquiryList", list);
        model.addAttribute("title", "inquiryIndex");

        return "inquiry/index";
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

}
