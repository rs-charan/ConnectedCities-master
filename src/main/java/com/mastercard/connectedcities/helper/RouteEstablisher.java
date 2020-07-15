package com.mastercard.connectedcities.helper;

import com.mastercard.connectedcities.constants.FileType;
import com.mastercard.connectedcities.exception.FileParsingException;
import com.mastercard.connectedcities.model.CityRouteTO;
import com.mastercard.connectedcities.util.FileParsingUtil;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Below class forms routes between all cities
 * It delegates request to {@link FileParsingUtil}
 * to parse content from input file
 */
@Component
public class RouteEstablisher {

    public RouteEstablisher() {}

    /**
     * Return {@link Map} with key as city and list
     * of values which are cities reachable from key
     * @return
     */
    public Map<String, List<String>> establishRoutes() {
        FileParsingUtil csvFileParsingUtil =  getFileParser();
        try {
            List<CityRouteTO> cityRouteTOList = csvFileParsingUtil.parseFileAt();
            return findAllConnectedCities(cityRouteTOList);
        } catch (Exception e) {
            throw new FileParsingException("Exception occurred while parsing file",e);
        }

    }

    /**
     * Below method retrieves file parser of CSV type , below
     * method is confined to retrieve parser of type CSV as it
     * is mentioned , however it can be improved by using factory
     * pattern to retrieve Parser dynamically based on file type
     * @return
     */
    public FileParsingUtil getFileParser() {
        return FileParsingUtil.getParserForFileType(FileType.CSV);
    }

    /**
     * Below method form city route map from connections obtained between each city
     * @param cityRouteTOList
     * @return
     */
    public Map<String,List<String>> findAllConnectedCities(List<CityRouteTO> cityRouteTOList) {
        if(null == cityRouteTOList || cityRouteTOList.isEmpty()) return Collections.emptyMap();

        Map<String,List<String>> cityRouteMap = new HashMap<>();
        for(CityRouteTO cityRouteTO : cityRouteTOList) {
            establishConnectionsBidirectionally(cityRouteMap, cityRouteTO.getSource(), cityRouteTO.getDestination());
            establishConnectionsBidirectionally(cityRouteMap, cityRouteTO.getDestination(), cityRouteTO.getSource());
        }
        return cityRouteMap;
    }

    /**
     * Below method is used to establish bi direction connection between
     * two cities A->B is connected implies B->A i also connected
     * @param cityRouteMap
     * @param source
     * @param destination
     */
    private void establishConnectionsBidirectionally(Map<String,List<String>> cityRouteMap, String source, String destination) {
        if(cityRouteMap.containsKey(source)) {
            List<String> citiesReachable = cityRouteMap.get(source);
            citiesReachable.add(destination);
        } else {
            List<String> citiesReachable = new ArrayList<>();
            citiesReachable.add(destination);
            cityRouteMap.put(source, citiesReachable);
        }
    }
}
