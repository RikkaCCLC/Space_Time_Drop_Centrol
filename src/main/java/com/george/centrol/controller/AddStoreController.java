package com.george.centrol.controller;

import com.george.centrol.mapper.StoreMapper;
import com.george.centrol.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AddStoreController {
    @Autowired
    StoreMapper storeMapper;
    @PostMapping("/centrol/addstore/")
    public Map<String,String> addStore(@RequestParam Map<String, String> map){
        String stoaddr = map.get("stoaddr");
        String stoname = map.get("stoname");
        Integer carnum = Integer.parseInt(map.get("carnum"));
        String username = map.get("username");
        String password = map.get("password");
        Double longitude = Double.parseDouble(map.get("longitude"));
        Double latitude = Double.parseDouble(map.get("latitude"));

        storeMapper.insert(new Store(
                null,
                stoname,
                stoaddr,
                carnum,
                username,
                password,
                longitude,
                latitude
        ));
        Map<String, String> newStoMap = new HashMap<>();
        newStoMap.put("error_message", "success");
        return newStoMap;
    }
}
