package com.qwb.demo.dao;

import com.qwb.demo.model.entity.SellerInfo;

import java.util.List;

public interface SellerInfoMapper {

    List<SellerInfo> findAllList();
}