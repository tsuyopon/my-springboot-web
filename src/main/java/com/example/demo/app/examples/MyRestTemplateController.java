package com.example.demo.app.examples;

import com.example.demo.app.examples.RestTemplateDto.RestDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

// RestTemplateを試してみるだけのクラス
// リクエスト先は https://httpbin.org/#/HTTP_Methods に試しています。

// リクエストのデバッグ出力を出力するためにはapplication.yamlに下記の設定を追加してください。
//    # RestTemplate用のデバッグログ
//    # see: https://www.baeldung.com/spring-resttemplate-logging
//    org.apache.http: DEBUG
//    httpclient.wire: DEBUG
//    org.springframework.web.client.RestTemplate: DEBUG

// 参考資料
// cf. https://www.baeldung.com/spring-rest-template-list

@RestController
@RequestMapping("/rest")
public class MyRestTemplateController {

    // 下記ドキュメントをみると、@AutowiredによりRestTemplateはDIできない模様。代わりに以下を呼び出す。
    // https://docs.spring.io/spring-boot/docs/1.4.0.RELEASE/reference/html/boot-features-restclient.html
    // > Since RestTemplate instances often need to be customized before being used, Spring Boot does not provide any single auto-configured RestTemplate bean
    private final RestTemplate restTemplate;
    public MyRestTemplateController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private static Log log = LogFactory.getLog(MyRestTemplateController.class);

    // public static final String URL1 = "http://httpbin.org/get";

    /************************************************************************
     *
     * GETリクエストサンプル
     *
     ***********************************************************************/

    // GETのWebAPIにリクエストをするサンプル (getForObject)
    @GetMapping("/get1")
    public String get1(Model model) {
        final String URL = "http://httpbin.org/get";
//        RestTemplate restTemplate = new RestTemplate();
        String r1 = restTemplate.getForObject(URL, String.class);
        return r1;
    }

    // GETのWebAPIにリクエストをするサンプル (getForObject)
    // RestDtoで取得
    @GetMapping("/get2")
    public String get2(Model model) {
        final String URL1 = "http://httpbin.org/get";
        RestTemplate restTemplate = new RestTemplate();
        RestDto r2 = restTemplate.getForObject(URL1, RestDto.class);
        System.out.println(r2.getHeaders().getAccept());
        System.out.println(r2.getHeaders().getUserAgent());
        System.out.println(r2.getHeaders().getHost());
        return "Request Completed";
    }

    // GETのWebAPIにリクエストをするサンプル (exchange)
    @GetMapping("/get3")
    public String get3(Model model) {
        log.debug("GET /rest/get3 start");

        // httpbinによりjsonが取得できる
        String url = "https://httpbin.org/get";
        String body = "";

        // MEMO: これ以降については、ステータスコードによる分岐は /rest/get1に存在するために割愛
        try {
            RestTemplate restTemplate = new RestTemplate();

            // 第３引数にはリクエスト情報、第４引数にはレスポンスボディの型を渡します(今回は指定していません)
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            //結果の取得
            HttpStatus status = response.getStatusCode();
            log.debug("StatusCode: " + status);
            body = response.getBody();

        } catch (HttpClientErrorException e) {
            //4xx系のエラー
            int status = e.getRawStatusCode();
            body = e.getResponseBodyAsString();
            log.debug("status code 4xx: "+status);
        } catch (HttpServerErrorException e) {
            //5xx系のエラー
            int status = e.getRawStatusCode();
            body = e.getResponseBodyAsString();
            log.debug("status code 4xx: "+status);
        }

        return body;
    }

    // GETのWebAPIにリクエストをするサンプル  (exchange)
    // クエリパラメータを{}としてテンプレート化するサンプル
    //  /rest/get1
    @GetMapping("/get4")
    public String get4(Model model) {

        log.debug("GET /rest/get4 start");

        // クエリパラメータ―は、URL を{}を用いてテンプレート化することで設定できます。
        String url = "https://httpbin.org/get?name={name}&age={age}";
        String name = "Tanaka";
        String age = "16";

        //リクエストの送信
        RestTemplate restTemplate = new RestTemplate();

        // 第５引数以降は可変長変数となっていて、{}の順番で値を指定する事ができます。
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class, name, age);

        //結果の取得
        String strBody = response.getBody();
        return strBody;
    }

    /************************************************************************
     *
     * POSTリクエストサンプル
     *
     ***********************************************************************/
    // POSTのWebAPIにリクエストをするサンプル (postForObjectの利用)
    // Case1: 単純なPOSTリクエスト
    // 参考元: https://itsakura.com/spring-resttemplate-post
    @GetMapping("/post1")
    public String post1(Model model) {

        String url = "http://httpbin.org/post";
        RestTemplate restTemplate = new RestTemplate();

        // リクエストはnull、戻り値はString.classを指定しておきます。
        String r1 = restTemplate.postForObject(url, null, String.class);
        //System.out.println(r1);

        return r1;
    }

    // POSTのWebAPIにリクエストをするサンプル (postForObjectの利用)
    // Case2: リクエストパラメータをHashMapに詰めて指定するリクエスト
    // 参考元: https://itsakura.com/spring-resttemplate-post
    @GetMapping("/post2")
    public String post2(Model model) {

        String url = "http://httpbin.org/post";
        RestTemplate restTemplate = new RestTemplate();

        // リクエストをHashMapに詰めてリクエストする。
        Map<String, String> color = new HashMap<>();
        color.put("a", "red");
        color.put("b", "blue");

        // リクエストを投げる。第２引数にはリクエストパラメータが指定される
        String r2 = restTemplate.postForObject(url, color, String.class);
        //System.out.println(r2);

        return r2;
    }

    // POSTのWebAPIにリクエストをするサンプル (postForObjectの利用)
    // 参考元: https://itsakura.com/spring-resttemplate-post
    @GetMapping("/post3")
    public String post3(Model model) {

        String url = "http://httpbin.org/post";
        RestTemplate restTemplate = new RestTemplate();

        // リクエスト情報
        RestDto r3 = restTemplate.postForObject(url, null, RestDto.class);
        log.info(r3.getHeaders().getAccept());// application/json, application/*+json
        log.info(r3.getHeaders().getUserAgent());// Java/11.0.11
        log.info(r3.getHeaders().getHost());// httpbin.org
        System.out.println(r3);

        return "Request Completed";
    }

//    // POSTのWebAPIにリクエストをするサンプル (exchangeの利用)
//    // 参考元: https://itsakura.com/spring-resttemplate-post
//    @GetMapping("/post4")
//    public String post4(Model model) {
//
//        log.debug("start /rest/post4");
//        String url = "http://httpbin.org/post";
//
//        // MEMO: 本当はLombokの@AllArgsConstructorを使いたかったが、うまくコンパイルできないために断念
//        class Sample {
//            private long id;
//            private String name;
//            private int age;
//            public Sample(final long id, final String name, final int age) {
//                this.id = id;
//                this.name = name;
//                this.age = age;
//            }
//        };
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // see https://stackoverflow.com/questions/21854369/no-suitable-httpmessageconverter-found-for-response-type
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);
//
//
////        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//
////        // ヘッダ情報生成
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////        //headers.setContentType(MediaType.APPLICATION_JSON);
//
//        final Sample sample = new Sample(10, "Suzuki Ichiro", 11);
////        final HttpEntity<Sample> entity = new HttpEntity<>(sample, null);
//
//        try {
//            String body = restTemplate.postForObject(url, sample, String.class);
//        }catch (RestClientException e) {
//            // 下記の遷移に入ってしまう。
//            log.info("ERROR: " + e.getMessage());
//        }
////
////        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
////        HttpStatus status = response.getStatusCode();
////        String body = response.getBody();
////        HttpHeaders resp_headers = response.getHeaders();
////        log.debug("StatusCode: " + status);
////        log.debug("Response Body: " + body);
////        log.debug("Response Headers: " + resp_headers);
//
//
//        // リクエスト情報
////        RestDto r3 = restTemplate.postForObject(url, null, RestDto.class);
////        log.info(r3.getHeaders().getAccept());// application/json, application/*+json
////        log.info(r3.getHeaders().getUserAgent());// Java/11.0.11
////        log.info(r3.getHeaders().getHost());// httpbin.org
////        System.out.println(r3);
//
//        return "Request Completed";
//    }

}