package com.george.centrol.service.impl;

import com.george.centrol.mapper.StoreMapper;
import com.george.centrol.service.SelectStoreNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectStoreNumImpl implements SelectStoreNum {
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List<Map<String, String>> getStoreNum() {
        return storeMapper.selectStoreNum();
    }
}
