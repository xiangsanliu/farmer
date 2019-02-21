package com.feidian.farmer.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Ingredient {

    private Long id;

    private String ingredientName;

    private Short amount;

}
