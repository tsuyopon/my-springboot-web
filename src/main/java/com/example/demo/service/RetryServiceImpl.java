package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RetryServiceImpl implements RetryService {

    // 単純に使ってみる例
    @Override
    public void myRetryService1(String param) throws Exception {

        // 0から9までのランダム値が取得できる
        Random rand = new Random();
        int num = rand.nextInt(10);
        System.out.println(num);

        // 0以外は全てエラーとみなす (@Retryableのテスト用途のため)
        if (num == 0){
            System.out.println("Successed");
        } else {
            System.out.println("Failed");
            throw new Exception();
        }
    }

    @Override
    public void myRetryService2(String param) throws Exception {
        this.myRetryService1(param+"2");
    }

    public void recover(Exception e, String sql){
        System.out.println("All Retry failed. recover entered");
        System.out.println(sql);
        System.out.println(e);
    }
}
