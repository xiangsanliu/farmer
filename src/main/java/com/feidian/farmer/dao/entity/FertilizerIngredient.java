package com.feidian.farmer.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class FertilizerIngredient {

    private Long fertilizerId;

    private Date fDate;

    private String fName;

    private Short fee;

    private Short lossRate;

    @JSONField(name = "id")
    private Long ingredientId;

    private String ingredientName;

    private Short amount;

}
