package com.mastercard.connectedcities.service;

import com.mastercard.connectedcities.helper.RouteEstablisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Below service class delegates request to
 * @{link RouteEstablisher} to retrieve city route map
 */
@Component
public class RouteFinderService {

    private RouteEstablisher routeEstablisher;

    @Autowired
    public RouteFinderService(RouteEstablisher routeEstablisher) {
        this.routeEstablisher = routeEstablisher;
    }

    /**
     * Below method accepts source and destination and finds if they
     * are connected
     * <ul>
     *     <li>Returns true if connected</li>
     *     <li>Returns false if disconnected</li>
     * </ul>
     * @param source
     * @param destination
     * @return
     */
    public boolean findSourceAndDestinationAreConnected(String source, String destination) {
        Map<String, List<String>> cityRouteMap = routeEstablisher.establishRoutes();

        if (cityRouteMap.get(source) == null || cityRouteMap.get(source).isEmpty()) return false;
        List<String> reachableCities = cityRouteMap.get(source);
        //If there is a direct path from source to destination
        if(reachableCities.contains(destination)) return true;

        if(traverseRecursively(cityRouteMap, source, destination, new HashSet<String>())) return true;
        return false;
    }

    /**
     * Below uses recursive approach (BFS) to find if path exists between cities
     * @param cityRouteMap
     * @param source
     * @param destination
     * @param visistedCities
     * @return
     */
    public boolean traverseRecursively(Map<String, List<String>> cityRouteMap, String source, String destination, Set<String> visistedCities) {
        Queue<String> adjacentCities = new ArrayDeque<>();

        visistedCities.add(source);
        adjacentCities.add(source);

        while (!adjacentCities.isEmpty())
        {

            String city = adjacentCities.poll();
            if (city.equals(destination)) {
                return true;
            }

            for (String  cities : cityRouteMap.get(city))
            {
                if (!visistedCities.contains(cities))
                {
                    visistedCities.add(cities);
                    adjacentCities.add(cities);
                }
            }
        }

        return false;
    }
}
