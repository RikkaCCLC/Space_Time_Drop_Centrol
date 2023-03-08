package com.george.centrol.service;

import java.util.List;
import java.util.Map;

//获取上月销量
public interface SelectLastMonthNum {
    public List<Map<String,String>> getLastMonthNum();
}
