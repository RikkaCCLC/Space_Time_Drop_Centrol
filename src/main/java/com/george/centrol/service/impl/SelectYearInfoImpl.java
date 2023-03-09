package com.george.centrol.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.george.centrol.consumer.WebSocketServer;
import com.george.centrol.mapper.CarNumMapper;
import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.service.SelectYearInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectYearInfoImpl implements SelectYearInfo {
    @Autowired
    private SaleInfoMapper saleInfoMapper;
    @Autowired
    private CarNumMapper carNumMapper;

    @Override
    public String getYearInfo() {
        List<Map<String,String>> list1 = saleInfoMapper.getCurYear();
        List<Map<String,String>> list2 = carNumMapper.getCurYearNum();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sale_num",JSON.toJSONString(list1));
        jsonObject.put("car_num",JSON.toJSONString(list2));
        return jsonObject.toJSONString();
    }
}
