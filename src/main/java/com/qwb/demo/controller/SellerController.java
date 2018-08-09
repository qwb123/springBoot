package com.qwb.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qwb.demo.annotation.JsonFilter;
import com.qwb.demo.model.entity.SellerInfo;
import com.qwb.demo.serviceImpl.SellerInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/seller")
@Controller
public class SellerController {

    @Autowired
    private SellerInfoServiceImpl sellerInfoService;

    @JsonFilter(type = PageInfo.class, include = "pageNum,pageSize,pages,total,list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<SellerInfo> getAllList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        Page<SellerInfo> page = PageHelper.startPage(pageNum, pageSize);
        sellerInfoService.findAllList();
        return page.toPageInfo();
    }


    @JsonFilter(type = PageInfo.class, include = "pageNum,pageSize,pages,total,list")
    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<SellerInfo> getAllList2(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        Page<SellerInfo> page = PageHelper.startPage(pageNum, pageSize);
        sellerInfoService.findAllList();
        return page.toPageInfo();
    }

}
