package com.guigu.msm.service;

import java.util.Map;

public interface MsmService {
    boolean sendMessage(Map<String, Object> map, String phone);
}
