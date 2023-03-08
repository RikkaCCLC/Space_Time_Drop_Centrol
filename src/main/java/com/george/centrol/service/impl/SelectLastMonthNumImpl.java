package com.george.centrol.service.impl;

import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.service.SelectLastMonthNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectLastMonthNumImpl implements SelectLastMonthNum {

    @Autowired
    private SaleInfoMapper saleInfoMapper;

    @Override
    public List<Map<String, String>> getLastMonthNum() {
        return saleInfoMapper.selectLastMonthSaleNum();
    }
}
