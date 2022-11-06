package com.example.demo.app.examples.RestTemplateDto;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 以下はhttpbinのPOSTエンドポイントにリクエストした際のサンプル
 */

// JSONレスポンスを受け取り、指定された要素を格納するクラス
public class Headers {

    // JSON中のAccept要素を格納する
    @JsonProperty("Accept")
    private String Accept;

    // JSON中のHost要素を格納する
    @JsonProperty("Host")
    private String Host;

    // JSON中のUser-Agent要素を格納する
    @JsonProperty("User-Agent")
    private String UserAgent;

    public String getAccept() {
        return Accept;
    }

    public String getHost() {
        return Host;
    }

    public String getUserAgent() {
        return UserAgent;
    }
}
