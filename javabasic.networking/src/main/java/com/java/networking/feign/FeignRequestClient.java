package com.java.networking.feign;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FeignRequestClient {

    // 指定配置Content的类型
    @RequestLine("GET /v1/chaos/rest/fast")
    @Headers("Content-Type: application/json;charset=UTF-8")
    void callChaosFast();

    // 将参数之间传递到URL @RequestParam中
    @RequestLine("GET /v1/chaos/rest/slow?duration={duration}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    void callChaosSlow(@Param("duration") Integer duration);

    // 在Header配置中使用配置参数
    // @RequestLine(value = "POST {uri}")
    // @Headers({ "Content-Type: application/json;charset=UTF-8", WatchDogManager.WATCH_DOG_INDEX + ": {index}" })
    // CallEndpointResponse callCustomPost(@Param("uri") String uri, @Param("index") int index, String payload);

    // 将传递的参数封装到request请求的body中
    // @RequestLine("POST {uri}")
    // @Headers({ "Content-Type: application/x-www-form-urlencoded", "Authorization: {Authorization}",
    //   WatchDogManager.WATCH_DOG_INDEX + ": {index}" })
    // @Body("{body}")
    // CallEndpointResponse callCustomUrlEncodedPost(@Param("uri") String uri,
    //                                               @Param("Authorization") String authorizationHeader,
    //                                               @Param("body") String body,
    //                                               @Param("index") int index) throws CallEndpointException;

    // 在请求的body中传递json数据
    @RequestLine("POST /")
    @Headers("Content-Type: application/xml")
    @Body("<login \"user_name\"=\"{user_name}\" \"password\"=\"{password}\"/>")
    void xml(@Param("user_name") String user, @Param("password") String password);
}
