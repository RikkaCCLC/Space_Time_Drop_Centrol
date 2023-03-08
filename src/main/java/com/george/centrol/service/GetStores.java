package com.george.centrol.service;

import com.george.centrol.pojo.Store;

import java.util.List;

//返回各门店的运营车辆，给予地图数据
public interface GetStores {
    public List<Store> getStores();
}
