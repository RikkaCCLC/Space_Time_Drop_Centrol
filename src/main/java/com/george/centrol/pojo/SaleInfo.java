package com.george.centrol.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("saleinfo")
public class SaleInfo {
    @TableId(value="saleid",type= IdType.AUTO)
    private Integer saleId;
    @TableField(value="stoid")
    private Integer stoId;
    @TableField(value="stoname")
    private String stoName;
    @TableField(value="stoaddr")
    private String stoAddr;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    @TableField(value="profit")
    private Double profit;
}
