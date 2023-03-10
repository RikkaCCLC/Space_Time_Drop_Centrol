package com.george.centrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.george.centrol.pojo.SaleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaleInfoMapper extends BaseMapper<SaleInfo> {

//    获取销售信息前8
    @Select("select * from saleinfo order by time desc limit 0,8")
    public List<SaleInfo> selectIndents();

//    获取昨日Top5销量门店
    @Select("select stoname,count(*) as store_sale " +
            "from saleinfo " +
            "where time between DATE_ADD(curdate(),INTERVAL -1 DAY) and curdate()  " +
            "group by stoname order by store_sale desc limit 0,5;")
    public List<Map<String,String>> selectTopFive();

//    获取今日销量
    @Select("select count(saleid) as today_sale_num from saleinfo " +
            "where time > curdate();")
    public List<Map<String,String>> selectTodaySaleNum();

//    获取本月销量
    @Select("select count(saleid) as curmonth_sale_num from saleinfo " +
            "where time >= DATE_ADD(curdate(),interval -day(curdate())+1 day);")
    public List<Map<String,String>> selectCurMonthSaleNum();

//    获取上个月销量
    @Select("select count(saleid) as lastmonth_sale_num from saleinfo " +
            "where time < DATE_ADD(curdate(),interval -day(curdate())+1 day) and " +
            "time >= DATE_ADD(curdate()-day(curdate())+1,interval -1 month);")
    public List<Map<String,String>> selectLastMonthSaleNum();

//    获取昨日销量
    @Select("select count(saleid) as yesterday_sale_num from saleinfo " +
            "where time >= DATE_ADD(curdate(),INTERVAL -1 day) " +
            "and time < curdate();")
    public List<Map<String,String>> selectYdaySaleNum();

//    获取本年销量信息，没有订单的一天用0填充，由ChatGPT生成，所以看起来复杂
    @Select("SELECT DATE_FORMAT(dates.date, '%Y-%m-%d') AS date,IFNULL(COUNT(saleinfo.time), 0) AS count FROM (SELECT  MAKEDATE(YEAR(CURDATE()), 1) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date FROM (SELECT 0 AS a UNION ALL SELECT 1  UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c) AS dates LEFT JOIN saleinfo ON DATE(saleinfo.time) = dates.date WHERE YEAR(dates.date) = YEAR(CURDATE()) GROUP BY dates.date;")
    public List<Map<String,String>> getCurYear();

    //获取本周利润
    @Select("SELECT SUM(profit) FROM saleinfo WHERE stoid = #{stoid} AND YEARWEEK(DATE_FORMAT(time,'%Y-%m-%d')) = YEARWEEK(NOW())")
    Double getWeekProfit(@Param("stoid") Integer stoid);

    //获取本月利润
    @Select("SELECT SUM(profit) FROM saleinfo WHERE stoid = #{stoid} AND YEAR(DATE_FORMAT(time,'%Y-%m-%d')) = YEAR(NOW()) AND MONTH(DATE_FORMAT(time,'%Y-%m-%d')) = MONTH(NOW())")
    Double getMonthProfit(@Param("stoid") Integer stoid);

    //获取上月利润
    @Select("SELECT SUM(profit) FROM saleinfo WHERE stoid = #{stoid} AND PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(time,'%Y%m'))=1")
    Double getLastMonthProfit(@Param("stoid") Integer stoid);

    @Select("SELECT COUNT(time) FROM saleinfo WHERE YEARWEEK(DATE_FORMAT(time,'%Y-%m-%d')) = YEARWEEK(NOW()) AND stoid = #{stoid}")
    Integer getWeekSale(@Param("stoid") Integer stoid);

    @Select("SELECT COUNT(time) FROM saleinfo WHERE stoid = #{stoid} AND YEAR(time) = YEAR(CURDATE()) AND MONTH(time) = MONTH(CURDATE())")
    Integer getMonthSale(@Param("stoid") Integer stoid);

    @Select("SELECT * FROM saleinfo ORDER BY time DESC LIMIT 8;")
    List<SaleInfo> getNewSale();

    //获取总的今日利润
    @Select("SELECT sum(profit) as today_profit from saleinfo where time >= curdate();")
    public List<Map<String,String>> getTodayProfit();

    //    获取总的本月利润
    @Select("select sum(profit) as cur_month_profit from saleinfo " +
            "where time >= DATE_ADD(curdate(),interval -day(curdate())+1 day);")
    public List<Map<String,String>> getCurMonthProfit();

    //    获取总的上个月利润
    @Select("select sum(profit) as last_month_profit from saleinfo " +
            "where time < DATE_ADD(curdate(),interval -day(curdate())+1 day) and " +
            "time >= DATE_ADD(curdate()-day(curdate())+1,interval -1 month);")
    public List<Map<String,String>> getLastMonthProfit();

}
