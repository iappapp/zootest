package com.github.iappapp.jdbc;

import com.github.iappapp.BaseTest;
import com.github.iappapp.dao.domain.Location;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

@Slf4j
public class LocationJdbcDaoTest extends BaseTest {
    @Autowired
    private LocationJdbcDao locationJdbcDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updateLocation() {
        int count = locationJdbcDao.updateLocation();
        log.info("updateLocation update {} record", count);
    }

    @Test
    public void queryLocation() {
        List<Location> locationList = locationJdbcDao.queryLocationList();
        log.info("queryLocation locationList={}", locationList);
        int result = JdbcTestUtils.countRowsInTable(jdbcTemplate, "basis_location");
        log.info("countRowsInTable result={}", result);
    }
}
