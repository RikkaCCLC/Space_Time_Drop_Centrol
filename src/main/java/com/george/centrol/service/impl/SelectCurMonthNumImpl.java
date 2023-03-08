package com.george.centrol.service.impl;

import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.service.SelectCurMonthNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectCurMonthNumImpl implements SelectCurMonthNum {
    @Autowired
    private SaleInfoMapper saleInfoMapper;
    @Override
    public List<Map<String, String>> getCurMonthNum() {
        return saleInfoMapper.selectCurMonthSaleNum();
    }
}
