package com.github.iappapp.service.impl;

import com.github.iappapp.dao.domain.Location;
import com.github.iappapp.dao.mapper.LocationExtMapper;
import com.github.iappapp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationExtMapper locationExtMapper;

    @Override
    @Transactional(value = "transactionManager")
    public List<Location> selectLocation() {
        return locationExtMapper.selectLocation(null);
    }

    @Transactional(value = "transactionManager")
    @Override
    public int updateLocation(Location location) {
        return locationExtMapper.updateLocation(location, "北京市");
    }
}
