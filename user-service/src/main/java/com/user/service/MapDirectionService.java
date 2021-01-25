package com.user.service;

import com.alibaba.fastjson.JSONObject;
import feign.Param;
import feign.RequestLine;


public interface MapDirectionService {

    /**
     * 获取公交路径
     *
     * @param key
     * @param origin
     * @param destination
     * @param city
     * @return
     */
    @RequestLine("GET /v3/direction/transit/integrated?key={key}&origin={fromPoint}&destination={destination}&city={city}")
    JSONObject getTransitMapDirection(@Param("key") String key, @Param("origin") String origin, @Param("destination") String destination, @Param("city") String city);

    /**
     * 获取步行路径
     *
     * @param key
     * @param origin
     * @param destination
     * @return
     */
    @RequestLine("GET /v3/direction/walking?key={key}&origin={origin}&destination={destination}")
    JSONObject getWalkMapDirection(@Param("key") String key, @Param("origin") String origin, @Param("destination") String destination);

}
