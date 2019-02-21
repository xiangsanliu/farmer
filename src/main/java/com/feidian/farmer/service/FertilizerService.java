package com.feidian.farmer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feidian.farmer.dao.entity.Fertilizer;
import com.feidian.farmer.dao.entity.Ingredient;
import com.feidian.farmer.dao.mapper.FertilizerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FertilizerService {

    @Resource
    private FertilizerMapper fertilizerMapper;

    public Map<String, List> getAllFertilizersAndIngredients() {
        Map<String, List> map = new HashMap<>();
        map.put("fertilizers", fertilizerMapper.selectAllFertilizers());
        map.put("ingredients", fertilizerMapper.selectAllIngredients());
        return map;
    }

    public List<Ingredient> getIngredientsByFertilizer(String data) {
        JSONObject obj = JSON.parseObject(data);
        long fId = obj.getLong("fId");
        return fertilizerMapper.selectIngredientsByFertilizer(fId);
    }

    @Transactional
    public void saveFI(String data) {
        Fertilizer fertilizer = JSON.parseObject(data, Fertilizer.class);
        fertilizerMapper.insertFertilizer(fertilizer);
        fertilizer.getIngredients().forEach(item -> item.setFertilizerId(fertilizer.getId()));
        if (fertilizer.getIngredients().size() > 0) {
            fertilizerMapper.insertFIs(fertilizer.getIngredients());
        }
    }

    public void removeById(String data) {
        JSONObject obj = JSON.parseObject(data);
        long fId = obj.getLong("fId");
        fertilizerMapper.deleteOne(fId);
    }

}
