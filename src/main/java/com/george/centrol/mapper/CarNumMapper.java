package com.george.centrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.george.centrol.pojo.CarNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarNumMapper extends BaseMapper<CarNum> {
    @Select("SELECT DATE_FORMAT(dates.date, '%Y-%m-%d') AS date,IFNULL(num, 0) AS count FROM (SELECT  MAKEDATE(YEAR(CURDATE()), 1) + INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS date FROM (SELECT 0 AS a UNION ALL SELECT 1  UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c) AS dates LEFT JOIN carnum ON DATE(carnum.time) = dates.date WHERE YEAR(dates.date) = YEAR(CURDATE()) GROUP BY dates.date;")
    public List<Map<String,String>> getCurYearNum();

}
