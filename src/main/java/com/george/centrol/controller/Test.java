package com.george.centrol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Test {

    @GetMapping("/test/")
    public Map<String, List<String>> test(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> saleId = new ArrayList<>();
        List<String> saleAddr = new ArrayList<>();
        List<String> saleName = new ArrayList<>();

        saleId.add(String.valueOf(5433));
        saleId.add(String.valueOf(5434));
        saleId.add(String.valueOf(5435));

        saleAddr.add("南京");
        saleAddr.add("北京");
        saleAddr.add("苏州");

        saleName.add("南京总店");
        saleName.add("北京京总店");
        saleName.add("苏州总店");
        map.put("sale_id",saleId);
        map.put("sale_addr",saleAddr);
        map.put("sale_name",saleName);
        return map;
    }

}
