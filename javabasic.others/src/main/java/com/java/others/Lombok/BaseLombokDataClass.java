package com.java.others.Lombok;

import java.time.LocalDate;

// @Data: 生成POJO (Plain old java object) 标准的Java Class的声明
// 自定义重写的EqualsAndHashCode方法所适用的参数是什么
//
// @Data包含以下所有的注解
//   A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields,
//   @Setter on all non-final fields, and @RequiredArgsConstructor
// @Data
// @EqualsAndHashCode(of = "id")
// @EqualsAndHashCode(of = {"id", "title"})
public class BaseLombokDataClass {
    
    private int id;
    private String title;
    private String details;
    private LocalDate deadline;

    // 构造器可以使用@AllArgsConstructor替代
    public BaseLombokDataClass(String title, String details, LocalDate deadline) {
        this.title = title;
        this.details = details;
        this.deadline = deadline;
    }
}
