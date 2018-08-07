package com.qwb.demo.dao;

import com.qwb.demo.DemoApplicationTests;
import com.qwb.demo.model.entity.SellerInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class SellerInfoMapperTest extends DemoApplicationTests {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @Test
    public void findAllList(){
       List<SellerInfo> list =  sellerInfoMapper.findAllList();
       for (SellerInfo s: list){
           System.out.println(s);
       }
    }
}