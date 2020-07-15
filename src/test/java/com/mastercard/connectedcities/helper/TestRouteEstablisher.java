package com.mastercard.connectedcities.helper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

@SpringBootTest
public class TestRouteEstablisher {

    @Autowired
    private RouteEstablisher routeEstablisher;

    private static  Map<String,List<String>> transitiveCityRouteMap;

    static {
        //{New York=[Boston], Newark=[Philadelphia, Boston], Trenton=[Albany], Philadelphia=[Newark], Boston=[New York, Newark], Albany=[Trenton]}
        transitiveCityRouteMap = new HashMap<>();
        List<String> pair1 = Arrays.asList("Boston");
        List<String> pair2 = Arrays.asList("Philadelphia","Boston");
        List<String> pair3 = Arrays.asList("Albany");
        List<String> pair4 = Arrays.asList("Newark");
        List<String> pair5 = Arrays.asList("New York","Newark");
        List<String> pair6 = Arrays.asList("Trenton");
        transitiveCityRouteMap.put("New York", pair1);
        transitiveCityRouteMap.put("Newark", pair2);
        transitiveCityRouteMap.put("Trenton", pair3);
        transitiveCityRouteMap.put("Philadelphia", pair4);
        transitiveCityRouteMap.put("Boston", pair5);
        transitiveCityRouteMap.put("Albany", pair6);
    }

    @Test
    public void test_establishRoutes() {
        Map<String,List<String>> routeMap = routeEstablisher.establishRoutes();
        Assert.isTrue((routeMap.entrySet().size()) == transitiveCityRouteMap.entrySet().size()
                , "Both maps are equal which is expected");
    }
}
