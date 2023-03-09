package com.george.centrol.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.george.centrol.consumer.WebSocketServer;
import com.george.centrol.pojo.SaleInfo;
import com.george.centrol.service.SelectSaleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SubToCentrolController {

    @Autowired
    private SelectSaleInfo selectSaleInfo;

    @GetMapping("/centrol/update_sale_data/")
    public void updateSaleData(){
        List<SaleInfo> list = selectSaleInfo.getSaleInfoList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","update_sale_data");
        jsonObject.put("data",JSON.toJSONString(list));
        String result = jsonObject.toJSONString();
        for(Map.Entry<Integer, WebSocketServer> entry : WebSocketServer.centrolUsers.entrySet()){
            WebSocketServer webSocketServer = entry.getValue();
            webSocketServer.sendMessage(result);
        }
    }

    @GetMapping("/centrol/up/salenum/")
    public void upSaleNum(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","up");
        String result = jsonObject.toJSONString();
        for(Map.Entry<Integer, WebSocketServer> entry : WebSocketServer.centrolUsers.entrySet()){
            WebSocketServer webSocketServer = entry.getValue();
            webSocketServer.sendMessage(result);
        }
    }

    @GetMapping("/centrol/down/salenum/")
    public void downSaleNum(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","down");
        String result = jsonObject.toJSONString();
        for(Map.Entry<Integer, WebSocketServer> entry : WebSocketServer.centrolUsers.entrySet()){
            WebSocketServer webSocketServer = entry.getValue();
            webSocketServer.sendMessage(result);
        }
    }
}
