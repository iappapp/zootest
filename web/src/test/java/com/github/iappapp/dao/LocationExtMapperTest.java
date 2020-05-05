package com.github.iappapp.dao;

import com.github.iappapp.BaseTest;
import com.github.iappapp.dao.domain.Location;
import com.github.iappapp.dao.mapper.LocationExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class LocationExtMapperTest extends BaseTest {
    @Autowired
    private LocationExtMapper locationExtMapper;

    @Test
    public void selectLocation() {
        List<Location> locationList = locationExtMapper.selectLocation(null);
        log.info("selectLocation locationList={}", locationList);
    }
}
