package com.github.iappapp.dao.mapper;

import com.github.iappapp.dao.domain.Location;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LocationExtMapper {

    int insertLocation(Location location);

    int batchInsertLocation(@Param("locationList") List<Location> locationList);

    @Delete(value = {"delete from basis_location where 1 = 1"})
    int deleteLocation();

    List<Location> selectLocation(Location location);

    @Flush
    void flush();

    @Update(value = "update basis_location set location= #{location.location} where location=#{oldLocation}")
    int updateLocation(@Param("location") Location location, @Param("oldLocation") String oldLocation);
}
