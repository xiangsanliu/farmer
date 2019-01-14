package com.feidian.farmer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feidian.farmer.dao.entity.Fertilizer;
import com.feidian.farmer.dao.entity.FertilizerIngredient;
import com.feidian.farmer.dao.entity.Ingredient;
import com.feidian.farmer.dao.entity.vo.FIVO;
import com.feidian.farmer.dao.mapper.FertilizerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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


    /**
     * 组装一对多的关系
     * 这段代码太丑了，一定要找个时间把它干掉！！！
     */
    @Transactional
    public void saveFI(String data) {
        FIVO object = JSON.parseObject(data, FIVO.class);
        Fertilizer fertilizer = new Fertilizer();
        fertilizer.setFDate(object.getFDate());
        fertilizer.setFName(object.getFName());
        fertilizer.setFee(object.getFee());
        fertilizer.setLossRate(object.getLossRate());
        fertilizerMapper.insertFertilizer(fertilizer);
        long fId = fertilizer.getId();
        List<FertilizerIngredient> fertilizerIngredients = new ArrayList<>();
        object.getIngredients().forEach(item -> {
            FertilizerIngredient fertilizerIngredient = new FertilizerIngredient();
            fertilizerIngredient.setFertilizerId(fId);
            fertilizerIngredient.setIngredientId(item.getId());
            fertilizerIngredient.setAmount(item.getAmount());
            fertilizerIngredients.add(fertilizerIngredient);
        });
        if (fertilizerIngredients.size() > 0) {
            fertilizerMapper.insertFIs(fertilizerIngredients);
        }
    }

    public void removeById(String data) {
        JSONObject obj = JSON.parseObject(data);
        long fId = obj.getLong("fId");
        fertilizerMapper.deleteOne(fId);
    }

}
