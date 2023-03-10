package com.george.centrol.controller;

import com.alibaba.fastjson2.JSONObject;
import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.pojo.SaleInfo;
import com.george.centrol.service.SubPageLeftService;
import com.george.centrol.service.SubPageRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SubController {
    @Autowired
    SubPageRightService subPageRightService;
    @Autowired
    SubPageLeftService subPageLeftService;

    @Autowired
    private SaleInfoMapper saleInfoMapper;

    @GetMapping("/sub/getNewList")
    public List<SaleInfo> getNewSale(){
        return subPageRightService.getNewSale();
    }

    @PostMapping("/sub/getfulllist/")
    public JSONObject getFullList(@RequestParam Map<String, String> map){
        return subPageLeftService.getFullList(map);
    }

    @GetMapping("/sub/totalprofit/")
    public Map<String,String> totalProfit(){
        Map<String,String> result = new HashMap<>();
        result.put("today_profit",saleInfoMapper.getTodayProfit().get(0).get("today_profit"));
        result.put("cur_month_profit",saleInfoMapper.getCurMonthProfit().get(0).get("cur_month_profit"));
        result.put("last_month_profit",saleInfoMapper.getLastMonthProfit().get(0).get("last_month_profit"));
        return result;
    }
}
