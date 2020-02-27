package com.github.iappapp.dao;

import com.github.iappapp.BaseTest;
import com.github.iappapp.dao.domain.CustInfo;
import com.github.iappapp.dao.mapper.CustInfoExtMapper;
import com.github.iappapp.service.CustInfoService;
import com.github.iappapp.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CustInfoExtMapperTest extends BaseTest {
    @Autowired
    private CustInfoExtMapper custInfoExtMapper;
    @Autowired
    private CustInfoService custInfoService;

    @Test
    public void listCustInfo() {
        List<CustInfo> custInfoList = custInfoExtMapper.listCustInfo(null);
        log.info("listCustInfo custInfoList={}", custInfoList);
    }

    @Test
    public void insertCustInfo() {
        CustInfo custInfo = new CustInfo();
        custInfo.setAge((short) 23);
        custInfo.setName("李连杰");
        custInfo.setId(new SnowflakeIdWorker(1, 1).nextId());
        custInfo.setSex(false);
        custInfo.setIdCardNo("341221199107296631");
        int count = custInfoService.insertCustInfo(custInfo);
        log.info("insertCustInfo insert {} record {}", count, custInfo);
    }
}
