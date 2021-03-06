package com.github.iappapp.service;

import com.github.iappapp.dao.domain.Location;

import java.util.List;

public interface LocationService {
    List<Location> selectLocation();

    int updateLocation(Location location, String oldLocation);

    Location location(Long id);
}
