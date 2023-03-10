package com.george.centrol.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.george.centrol.mapper.SaleInfoMapper;
import com.george.centrol.mapper.StoreMapper;
import com.george.centrol.pojo.SaleInfo;
import com.george.centrol.pojo.Store;
import com.george.centrol.service.SubPageLeftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SubPageLeftServiceImpl implements SubPageLeftService {
    @Autowired
    SaleInfoMapper saleInfoMapper;

    @Autowired
    StoreMapper storeMapper;

    @Override
    public JSONObject getFullList(Map<String, String> map) {
        Integer page = Integer.valueOf(map.get("page"));
        IPage<SaleInfo> saleInfoIPage = new Page<>(page, 10);
        QueryWrapper<SaleInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("saleid");
        List<SaleInfo> saleInfos = saleInfoMapper.selectPage(saleInfoIPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        List<JSONObject> items = new LinkedList<>();


        for (SaleInfo saleInfo : saleInfos) {
            //获取当前门店的所有saleInfo
            saleInfo = saleInfoMapper.selectById(saleInfo.getSaleId());
            JSONObject item = new JSONObject();
            Integer stoid = Integer.valueOf(saleInfo.getStoId());
            Store store = storeMapper.selectById(stoid);
            //根据当前门店的id获取本周,本月,上月利润
            Double weekProfit = saleInfoMapper.getWeekProfit(stoid);
            Double monthProfit = saleInfoMapper.getMonthProfit(stoid);
            Double lastMonthProfit = saleInfoMapper.getLastMonthProfit(stoid);
            Integer weekSale = saleInfoMapper.getWeekSale(stoid);
            Integer monthSale = saleInfoMapper.getMonthSale(stoid);

            item.put("stoId", String.valueOf(stoid));
            item.put("stoAddr", saleInfo.getStoAddr());
            item.put("stoName", saleInfo.getStoName());
            item.put("carnum", store.getCarNum());
            item.put("weekProfit",  String.valueOf(weekProfit));
            item.put("monthProfit", String.valueOf(monthProfit));
            item.put("lastMonthProfit", String.valueOf(lastMonthProfit));
            item.put("weekSale", String.valueOf(weekSale));
            item.put("monthSale", String.valueOf(monthSale));

            item.put("error_message", "success");
            items.add(item);
        }
            resp.put("leftinfos",items);
            resp.put("leftinfos_count",storeMapper.selectCount(null));
            return resp;
    }

}
