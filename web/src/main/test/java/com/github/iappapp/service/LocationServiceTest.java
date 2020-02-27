package com.github.iappapp.service;

import com.github.iappapp.BaseTest;
import com.github.iappapp.dao.domain.Location;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationServiceTest extends BaseTest {
    @Autowired
    private LocationService locationService;


    @Test
    public void test() {
        locationService.updateLocation(new Location(null, "北京市", null));
    }
}
