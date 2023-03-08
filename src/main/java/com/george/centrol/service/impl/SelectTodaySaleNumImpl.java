package com.george.centrol.service.impl;

import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.service.SelectTodaySaleNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectTodaySaleNumImpl implements SelectTodaySaleNum {
    @Autowired
    private SaleInfoMapper saleInfoMapper;
    @Override
    public List<Map<String, String>> selectTodaySaleNum() {
        return saleInfoMapper.selectTodaySaleNum();
    }
}
