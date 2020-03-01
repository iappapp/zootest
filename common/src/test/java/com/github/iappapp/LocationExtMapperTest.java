package com.github.iappapp;

import com.alibaba.fastjson.JSONObject;
import com.github.iappapp.dao.domain.Location;
import com.github.iappapp.dao.mapper.LocationExtMapper;
import com.github.iappapp.util.SnowflakeIdWorker;
import com.github.iappapp.util.SqlSessionFactoryUtil;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.util.*;
import java.util.stream.Collectors;

public class LocationExtMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(LocationExtMapperTest.class);

    // @Before
    public void deleteLocation() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();
        LocationExtMapper locationExtMapper = session.getMapper(LocationExtMapper.class);
        int count = locationExtMapper.deleteLocation();
        logger.info("deleteLocation delete {} record", count);
        session.commit(true);
    }

    @Test(expected = Exception.class)
    public void insertLocation() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();
        LocationExtMapper locationExtMapper = session.getMapper(LocationExtMapper.class);
        Location location = new Location();
        location.setId(new SnowflakeIdWorker(0, 0).nextId());
        location.setLocationNo("110000");
        location.setLocation("北京市");
        locationExtMapper.insertLocation(location);
        session.commit(true);
    }

    @Test
    public void batchInsertLocation() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();
        LocationExtMapper locationExtMapper = session.getMapper(LocationExtMapper.class);
        Location location = new Location();
        location.setId(new SnowflakeIdWorker(0, 0).nextId());
        location.setLocationNo("110000");
        location.setLocation("北京市");
        int count = locationExtMapper.batchInsertLocation(Lists.newArrayList(location));
        logger.info("batchInsertLocation count={}", count);
        session.commit(true);
    }

    @Test
    public void insertBatch() throws Exception {
        Resource resource = new DefaultResourceLoader().getResource("data_location.json");
        logger.info("insertLocation json={}", resource.getFilename());
        logger.info("insertLocation json={}", resource.exists());

        Map<String, String> object = JSONObject.parseObject(resource.getInputStream(), Map.class);

        List<Location> locationList = Lists.newArrayList();
        for (Map.Entry<String, String> entry : object.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
            Location location = new Location();
            location.setId(new SnowflakeIdWorker(31, 31).nextId() + new Random().nextInt());
            location.setLocationNo(entry.getKey());
            location.setLocation(entry.getValue());
            locationList.add(location);
        }

        SqlSession session = SqlSessionFactoryUtil.getSqlSession();
        LocationExtMapper locationExtMapper = session.getMapper(LocationExtMapper.class);
        int count = locationExtMapper.batchInsertLocation(locationList);
        logger.info("batchInsertLocation count={}", count);

        session.commit();
    }

    @Test
    public void selectLocation() {
        LocationExtMapper locationExtMapper = SqlSessionFactoryUtil.getMapper(LocationExtMapper.class);

        List<Location> locationList = locationExtMapper.selectLocation(null);
        logger.info("locationList={}", locationList);
        Assert.assertTrue(locationList != null && locationList.size() > 0);

        locationList = locationExtMapper.selectLocation(new Location(null, "北京市", null));
        logger.info("locationList={}", locationList);
        Assert.assertTrue(locationList != null && locationList.size() == 1);

        locationList = locationExtMapper.selectLocation(new Location(null, "北京市", null));
        logger.info("locationList={}", locationList);
        Assert.assertTrue(locationList != null && locationList.size() == 1);
    }

    @Test
    public void updateLocation() {
        LocationExtMapper locationExtMapper = SqlSessionFactoryUtil.getMapper(LocationExtMapper.class);

        int result =
                locationExtMapper.updateLocation(new Location(null, "北京市", null), "北京市+");
        logger.info("updateLocation update {} record", result);

    }

    @Test
    public void flushAll() {
        LocationExtMapper locationExtMapper = SqlSessionFactoryUtil.getMapper(LocationExtMapper.class);

        locationExtMapper.flush();
        logger.info("updateLocation flush finish");
    }

    @Test
    public void collectLocationTree() throws Exception {
        Resource resource = new DefaultResourceLoader().getResource("data_location.json");
        logger.info("insertLocation json={}", resource.getFilename());
        logger.info("insertLocation json={}", resource.exists());

        Map<String, String> object = JSONObject.parseObject(resource.getInputStream(), Map.class);

        List<Location> provinceList = Lists.newArrayList();
        List<Location> cityList = Lists.newArrayList();
        List<Location> townList = Lists.newArrayList();

        for (Map.Entry<String, String> entry : object.entrySet()) {
            if (entry.getKey().endsWith("0000")) {
                provinceList.add(new Location(null, entry.getValue(), entry.getKey()));
            } else if (entry.getKey().endsWith("00")) {
                cityList.add(new Location(null, entry.getValue(), entry.getKey()));
            } else {
                townList.add(new Location(null, entry.getValue(), entry.getKey()));
            }
        }


        Collections.sort(provinceList, Comparator.comparing(Location::getLocationNo));

        Collections.sort(cityList, Comparator.comparing(Location::getLocationNo));

        Collections.sort(townList, Comparator.comparing(Location::getLocationNo));

        for (Location location : provinceList) {
            System.out.println(location.getLocation());
            List<Location> ofCityList = collectCity(location.getLocationNo(), cityList);
            for (Location city : ofCityList) {
                System.out.println("--\t" + city.getLocation());
                List<Location> ofTownList = collectTown(city.getLocationNo(), townList);
                for (Location town : ofTownList) {
                    System.out.println("----\t" + town.getLocation());
                }
            }
        }
    }

    public List<Location> collectCity(String province, List<Location> locationList) {

        return locationList.stream()
                .filter(d -> d.getLocationNo().startsWith(province.substring(0, 2)))
                .collect(Collectors.toList());
    }

    public List<Location> collectTown(String city, List<Location> locationList) {

        return locationList.stream()
                .filter(d -> d.getLocationNo().startsWith(city.substring(0, 4)))
                .collect(Collectors.toList());
    }
}

