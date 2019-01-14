package com.feidian.farmer.controller;

import com.feidian.farmer.service.FertilizerService;
import com.feidian.farmer.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FertilizerController {

    private final FertilizerService fertilizerService;


    @Autowired
    public FertilizerController(FertilizerService fertilizerService) {
        this.fertilizerService = fertilizerService;
    }

    @RequestMapping(value = "/createFI", method = RequestMethod.POST)
    public ResponseBean createFI(@RequestBody String data) {
        fertilizerService.saveFI(data);
        return ResponseBean.ok("添加成功");
    }

    @RequestMapping(value = "/getAllFertilizersAndIngredients", method = RequestMethod.GET)
    public ResponseBean getAllFertilizersAndIngredients() {
        return ResponseBean.ok("success", fertilizerService.getAllFertilizersAndIngredients());
    }

    @RequestMapping(value = "/getIngredientsByFertilizer", method = RequestMethod.POST)
    public ResponseBean getIngredientsByFertilizer(@RequestBody String data) {
        return ResponseBean.ok("success", fertilizerService.getIngredientsByFertilizer(data));
    }

    @RequestMapping(value = "/removeFI", method = RequestMethod.POST)
    public ResponseBean removeFI(@RequestBody String data) {
        fertilizerService.removeById(data);
        return ResponseBean.ok("删除成功");
    }

}
