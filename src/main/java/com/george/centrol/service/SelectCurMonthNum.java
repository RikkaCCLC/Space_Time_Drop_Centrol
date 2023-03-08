package com.george.centrol.service;

import java.util.List;
import java.util.Map;

//获取当月销量
public interface SelectCurMonthNum {
    public List<Map<String,String>> getCurMonthNum();
}
