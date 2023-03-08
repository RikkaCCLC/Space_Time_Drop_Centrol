package com.george.centrol.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @TableId(value="stoid",type= IdType.AUTO)
    private Integer stoId;
    @TableField(value="stoname")
    private String stoName;
    @TableField(value="stoaddr")
    private String sioAddr;
    @TableField(value="carnum")
    private Integer carNum;
    @TableField(value="username")
    private String username;
    @TableField(value="password")
    private String password;
    @TableField(value="longitude")
    private Double longitude;
    @TableField(value="latitude")
    private Double latitude;
}
