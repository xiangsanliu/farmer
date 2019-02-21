package com.feidian.farmer.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class Fertilizer {

    private Long id;

    private Date fDate;

    private String fName;

    private Short fee;

    private Short lossRate;

    private List<FertilizerIngredient> ingredients;

}
