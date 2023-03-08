package com.george.centrol.service;

import java.util.List;
import java.util.Map;

// 获取销量前五
public interface SelectTopFive {
    public List<Map<String, String>> selectTopFive();
}
