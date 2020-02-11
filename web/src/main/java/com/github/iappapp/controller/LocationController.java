package com.github.iappapp.controller;

import com.github.iappapp.dao.domain.Location;
import com.github.iappapp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> selectLocation() {
        return locationService.selectLocation();
    }
}
