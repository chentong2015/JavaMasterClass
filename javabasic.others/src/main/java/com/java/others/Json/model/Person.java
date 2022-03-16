package com.java.others.Json.model;

import com.fasterxml.jackson.annotation.JsonInclude;

// 在对象序列化成json的过程中，如果有属性的值是null，则不对该属性进行json序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    public String firstName = "chen";
    public String middleName;
    public String lastName = "tong";
}
