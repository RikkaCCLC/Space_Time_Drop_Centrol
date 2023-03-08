package com.george.centrol.service.impl;

import com.alibaba.fastjson.JSON;
import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.service.SelectYearInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelectYearInfoImpl implements SelectYearInfo {
    @Autowired
    private SaleInfoMapper saleInfoMapper;

    @Override
    public String getYearInfo() {
        String result = JSON.toJSONString(saleInfoMapper.getCurYear());
        return result;
    }
}
