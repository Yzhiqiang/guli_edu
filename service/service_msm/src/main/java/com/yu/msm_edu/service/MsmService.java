package com.yu.msm_edu.service;

import java.util.Map;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 13:22 2021/11/17
 * @Modified By:
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
