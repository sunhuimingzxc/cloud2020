package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JSON封装体，和前端沟通的类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code; //404 500
    private String message;//提示消息
    private T data; //具体JSON数据

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }

}
