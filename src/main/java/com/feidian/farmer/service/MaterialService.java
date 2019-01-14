package com.feidian.farmer.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feidian.farmer.dao.entity.PurchaseRecord;
import com.feidian.farmer.dao.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaterialService {

    @Resource
    public MaterialMapper materialMapper;


    public Map<String, List> getAll() {
        Map<String, List> map = new HashMap<>();
        map.put("materials", materialMapper.selectAllMaterials());
        map.put("purchaseRecords", materialMapper.selectAllRecords());
        return map;
    }

    public void save(PurchaseRecord purchaseRecord) {
        materialMapper.insertOne(purchaseRecord);
    }

    public void removeById(String data) {
        JSONObject obj = JSON.parseObject(data);
        long rId = obj.getLong("rId");
        materialMapper.deleteOne(rId);
    }

}
