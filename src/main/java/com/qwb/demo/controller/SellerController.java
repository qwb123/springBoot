package com.qwb.demo.controller;

import com.qwb.demo.model.entity.SellerInfo;
import com.qwb.demo.serviceImpl.SellerInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/seller")
@EnableAutoConfiguration
public class SellerController {

    @Autowired
    private SellerInfoServiceImpl sellerInfoService;

    @RequestMapping("/list")
    @ResponseBody
    public List<SellerInfo> getAllList(){
        List<SellerInfo> list = sellerInfoService.findAllList();
        for (SellerInfo s:list){
            System.out.println(s);
        }
        return sellerInfoService.findAllList();
    }
}
