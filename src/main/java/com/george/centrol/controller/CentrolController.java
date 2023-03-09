package com.george.centrol.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.george.centrol.mapper.CentrolMapper;
import com.george.centrol.pojo.Centrol;
import com.george.centrol.pojo.Store;
import com.george.centrol.service.*;
import com.george.centrol.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CentrolController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SelectTopFive selectTopFive;
    @Autowired
    private SelectTodaySaleNum selectTodaySaleNum;
    @Autowired
    private SelectCarNum selectCarNum;
    @Autowired
    private SelectCurMonthNum selectCurMonthNum;
    @Autowired
    private SelectLastMonthNum selectLastMonthNum;
    @Autowired
    private SelectStoreNum selectStoreNum;
    @Autowired
    private SelectYdaySaleNum selectYdaySaleNum;
    @Autowired
    private GetStores getStores;

    @Autowired
    private CentrolMapper centrolMapper;

    @Autowired
    private SelectYearInfo selectYearInfo;

    @PostMapping("/centrol/login/token/")
    public Map<String,String> getToken(@RequestParam Map<String, String> map){
        String username = map.get("username");
        String password = map.get("password");
        return loginService.getToken(username, password);
    }

//    判断token是否合法
    @PostMapping("/centrol/legal_token/")
    public Map<String,String> isTokenLegal(@RequestParam Map<String, String> map){
        String token = map.get("token");
        Map<String,String> result = new HashMap<>();
        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
            result.put("error_message","illegal");
        }
        token = token.substring(7);
        Claims claims;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Date now = new Date();
        if(now.after(claims.getExpiration())){
            result.put("error_message","illegal");
        } else {
            result.put("error_message","legal");
            String userid = claims.getSubject();
            result.put("id",userid);
            Centrol centrolUser = centrolMapper.selectById(Integer.parseInt(userid));
            result.put("username",centrolUser.getUsername());
        }
        return result;
    }

//    获取销量前五
    @GetMapping("/centrol/topfive/")
    public String getTopFive(){
        String result = JSON.toJSONString(selectTopFive.selectTopFive());
        return result;
    }

//    获取今日销量、昨日销量、本月销量、上月销量,运营车辆、门店数量
    @GetMapping("/centrol/init_saleinfo/")
    public String initSaleInfo(){
        List<Map<String, String>> todaySaleNum = selectTodaySaleNum.selectTodaySaleNum();
        List<Map<String, String>> yesterdaySaleNum = selectYdaySaleNum.selectYdaySaleNum();
        List<Map<String, String>> curMonthSaleNum = selectCurMonthNum.getCurMonthNum();
        List<Map<String, String>> lastMonthSaleNum = selectLastMonthNum.getLastMonthNum();
        List<Map<String, String>> carNum = selectCarNum.getCarNum();
        List<Map<String, String>> storeNum = selectStoreNum.getStoreNum();
        List<Map<String, String>> total = new ArrayList<>();
        total.add(todaySaleNum.get(0));
        total.add(yesterdaySaleNum.get(0));
        total.add(curMonthSaleNum.get(0));
        total.add(lastMonthSaleNum.get(0));
        total.add(carNum.get(0));
        total.add(storeNum.get(0));
        String result = JSON.toJSONString(total);
        return result;
    }

    @GetMapping("/centrol/map/")
    public String getMapData(){
        List<Store> stores = getStores.getStores();
        JSONObject jsonObject = new JSONObject();
        for(Store t : stores){
            jsonObject.put(t.getStoName(),t.getCarNum());
        }
        return jsonObject.toJSONString();
    }

//    返回年度销售信息
    @GetMapping("/centrol/get_year_info/")
    public String getYearInfo(){
        return selectYearInfo.getYearInfo();
    }
}
