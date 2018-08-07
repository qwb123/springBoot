package com.qwb.demo.serviceImpl;

import com.qwb.demo.dao.SellerInfoMapper;
import com.qwb.demo.model.entity.SellerInfo;
import com.qwb.demo.service.SellerInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    private SellerInfoMapper sellerInfoMapper;

    @Override
    public List<SellerInfo> findAllList() {
        return sellerInfoMapper.findAllList();
    }
}
