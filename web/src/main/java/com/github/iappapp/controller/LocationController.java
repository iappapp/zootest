package com.github.iappapp.controller;

import com.github.iappapp.dao.domain.Location;
import com.github.iappapp.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/location")
@Slf4j
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> selectLocation() {
        log.info("location list start");
        return locationService.selectLocation();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public String update(Location location, String oldLocation) {
        log.info("update location={}", location);
        locationService.updateLocation(location, oldLocation);
        return "OK";
    }

    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ResponseBody
    public Location selectById(Long id) {
        return locationService.location(id);
    }
}
