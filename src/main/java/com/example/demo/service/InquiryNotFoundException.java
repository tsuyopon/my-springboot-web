package com.example.demo.service;

/**
 * 独自で例外ハンドラを作成する
 */

// RuntimeExceptionを継承する
public class InquiryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InquiryNotFoundException(String message) {
        super(message);
    }
}
