package com.george.centrol.service.impl;

import com.george.centrol.mapper.StoreMapper;
import com.george.centrol.pojo.Store;
import com.george.centrol.service.SelectCarNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectCarNumImpl implements SelectCarNum {
    @Autowired
    private StoreMapper storeMapper;
    @Override
    public List<Map<String, String>> getCarNum() {
        return storeMapper.selectCarNum();
    }
}
