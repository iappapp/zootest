package com.github.iappapp.service.impl;

import com.github.iappapp.dao.domain.CustInfo;
import com.github.iappapp.dao.domain.Location;
import com.github.iappapp.dao.mapper.LocationExtMapper;
import com.github.iappapp.service.CustInfoService;
import com.github.iappapp.service.LocationService;
import com.github.iappapp.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationExtMapper locationExtMapper;
    @Autowired
    private CustInfoService custInfoService;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public List<Location> selectLocation() {
        return locationExtMapper.selectLocation(null);
    }

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    @Override
    public int updateLocation(Location location) {
        int result = locationExtMapper.updateLocation(location, "北京市");
        log.info("updateLocation update {} record", result);
        CustInfo custInfo = new CustInfo();
        custInfo.setAge((short) 23);
        custInfo.setName("李连杰");
        custInfo.setId(new SnowflakeIdWorker(1, 1).nextId());
        custInfo.setSex(false);
        custInfo.setIdCardNo("341221199107296631");
        custInfoService.insertCustInfo(custInfo);

        return result;
    }
}
