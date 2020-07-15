package com.mastercard.connectedcities.service;

import com.mastercard.connectedcities.helper.RouteEstablisher;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

@SpringBootTest
public class TestRouteFinder {

    private static  Map<String, List<String>> cityRouteMap;
    private static  Map<String,List<String>> transitiveCityRouteMap;

    static {
        //Boston=[New York, Newark]
        cityRouteMap = new HashMap<>();
        List<String> cityList = new ArrayList<>();
        cityList.add("New York");
        cityList.add("Newark");
        cityRouteMap.put("Boston",cityList);

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
    @Autowired
    private RouteFinderService routeFinderService;

    @Mock
    private RouteEstablisher routeEstablisher;

    @Test
    public void test_findSourceAndDestinationAreConnected() {
        Mockito.when(routeEstablisher.establishRoutes()).thenReturn(cityRouteMap);
        Assert.isTrue(routeFinderService.findSourceAndDestinationAreConnected("Boston","Newark"),"There exists connectivity b/w Boston and Newark");
    }

    @Test
    public void test_transitive_sourceAndDestinationAreConnected() {
        Mockito.when(routeEstablisher.establishRoutes()).thenReturn(transitiveCityRouteMap);
        Assert.isTrue(routeFinderService.findSourceAndDestinationAreConnected("Boston","Philadelphia"),
                "There exists connectivity b/w Boston and Philadelphia");
    }

}
