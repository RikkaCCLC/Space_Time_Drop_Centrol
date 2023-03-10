package com.george.centrol.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.pojo.SaleInfo;
import com.george.centrol.service.SubPageRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubPageRightServiceImpl implements SubPageRightService {
    @Autowired
    SaleInfoMapper saleInfoMapper;
    @Override
    public List<SaleInfo> getNewSale() {
        return saleInfoMapper.getNewSale();
    }
}
