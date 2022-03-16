package com.java.others.Json.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// 从Json转换成object的时候，忽略无法识别的属性
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    public String firstName;
    public String lastName;
}
