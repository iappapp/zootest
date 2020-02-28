package com.github.iappapp.service.impl;

import com.github.iappapp.dao.domain.CustInfo;
import com.github.iappapp.dao.mapper.CustInfoExtMapper;
import com.github.iappapp.service.CustInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CustInfoServiceImpl implements CustInfoService {
    @Autowired
    private CustInfoExtMapper custInfoExtMapper;

    @Override
    public List<CustInfo> listCustInfo(CustInfo custInfo) {
        return custInfoExtMapper.listCustInfo(custInfo);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public int insertCustInfo(CustInfo custInfo) {
        log.info("insertCustInfo insert custInfo={}", custInfo);
        int result = custInfoExtMapper.insertCustInfo(custInfo);
        return result;
    }
}
