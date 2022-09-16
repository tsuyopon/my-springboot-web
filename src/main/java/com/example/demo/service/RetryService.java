package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface RetryService {

    // 5回のリトライを、100m秒単位の間隔で行う。
    @Retryable(value = {Exception.class} , maxAttempts = 5, backoff = @Backoff(delay = 100))
    void myRetryService1(String param) throws Exception;

    // RetryConfig.javaで指定された@PropertySource(value = "classpath:retry.properties")を元にretry.propertiesから取得する
    // 設定値が${xxx.yyy}で値が取得できない場合にはIllegalArgumentExceptionがthrowされる。
    // デフォルト値を取得するには #{${retry.maxAttempts:4}}などとすると良い
    @Retryable(value = {Exception.class} , maxAttemptsExpression = "#{${retry.maxAttempts:4}}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    void myRetryService2(String param) throws Exception;

    // 全てのretry処理(maxAttempts回)が失敗した場合に呼ばれる処理 (このメソッドはなくても良い)
    @Recover
    void recover(Exception e, String sql);
}