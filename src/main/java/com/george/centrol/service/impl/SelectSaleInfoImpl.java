package com.george.centrol.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.pojo.SaleInfo;
import com.george.centrol.service.SelectSaleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectSaleInfoImpl implements SelectSaleInfo {
    @Autowired
    private SaleInfoMapper saleInfoMapper;

    @Override
    public List<SaleInfo> getSaleInfoList() {
        List<SaleInfo> list = saleInfoMapper.selectIndents();
        return list;
    }
}
