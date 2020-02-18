package com.github.iappapp.jdbc;

import com.github.iappapp.dao.domain.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class LocationJdbcDao implements RowMapper<Location> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        location.setLocation(rs.getString("location"));
        location.setLocationNo(rs.getString("location_no"));
        location.setId(rs.getLong("id"));
        return location;
    }

    public int updateLocation() {
        Object[] args = {"北京市", "北京市"};
        int result = jdbcTemplate.update("update basis_location set location= ? where location= ?", args);
        log.info("updateLocation result={}", result);
        return result;
    }

    public List<Location> queryLocationList() {
        String sql = "select id, location, location_no from basis_location";
        return jdbcTemplate.query(sql, this);
    }

    public int updateLocationByPreparedSetter() {
        Object[] args = {"北京市", "北京市"};
        PreparedStatementSetter setter = new ArgumentPreparedStatementSetter(args);
        int result = jdbcTemplate.update("update basis_location set location= ? where location= ?", setter);
        log.info("updateLocation result={}", result);
        return result;
    }

    public int updateLocationByStatementCreator() {
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("update basis_location set location='北京市' where location='北京市'");
            }
        };
        int result = jdbcTemplate.update(creator);
        log.info("updateLocationByStatementCreator update {} record", result);
        return result;
    }

}
