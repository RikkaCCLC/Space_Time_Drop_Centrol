package com.george.centrol.service.impl;

import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.service.SelectYdaySaleNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectYdaySaleNumImpl implements SelectYdaySaleNum {
    @Autowired
    private SaleInfoMapper saleInfoMapper;

    @Override
    public List<Map<String, String>> selectYdaySaleNum() {
        return saleInfoMapper.selectYdaySaleNum();
    }
}
