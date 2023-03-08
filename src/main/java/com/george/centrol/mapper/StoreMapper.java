package com.george.centrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.george.centrol.pojo.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreMapper extends BaseMapper<Store> {
    @Select("select sum(carnum) as carnum from store;")
    public List<Map<String,String>> selectCarNum();

    @Select("select count(stoid) as store_num from store;")
    public List<Map<String,String>> selectStoreNum();
}
