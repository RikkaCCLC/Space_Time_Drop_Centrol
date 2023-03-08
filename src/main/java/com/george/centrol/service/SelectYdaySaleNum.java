package com.george.centrol.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

//获取昨日销量
public interface SelectYdaySaleNum {
    public List<Map<String,String>> selectYdaySaleNum();
}
