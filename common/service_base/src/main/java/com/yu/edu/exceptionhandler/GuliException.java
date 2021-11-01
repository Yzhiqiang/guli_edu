package com.yu.edu.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:54 2021/10/31
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    private Integer code;   //异常状态码

    private String msg;

}
