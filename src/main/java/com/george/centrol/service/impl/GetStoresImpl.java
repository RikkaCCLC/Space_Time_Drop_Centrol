package com.george.centrol.service.impl;

import com.george.centrol.mapper.StoreMapper;
import com.george.centrol.pojo.Store;
import com.george.centrol.service.GetStores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetStoresImpl implements GetStores {
    @Autowired
    private StoreMapper storeMapper;
    @Override
    public List<Store> getStores() {
        return storeMapper.selectList(null);
    }
}
