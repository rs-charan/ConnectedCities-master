package com.mastercard.connectedcities.util;

import com.mastercard.connectedcities.exception.InvalidRoutePair;
import com.mastercard.connectedcities.model.CityRouteTO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Below class wraps logic of parsing {@link com.mastercard.connectedcities.constants.FileType#CSV}
 * <ul>
 *     <li>It parses given csv file at location</li>
 *     <li>Maps it to {@link CityRouteTO}</li>
 * </ul>
 */
@Component
public class CsvFileParsingUtil extends FileParsingUtil {

    private static final String WORD_SEPARATOR = ",";

    /**
     * Parses file at specified location in classpath
     * Below method uses hard coded WORD separator as
     * name specifies it is a CSVFileParsingUtil
     * @return
     * @throws Exception
     */
    @Override
    public List<CityRouteTO> parseFileAt() throws Exception{
        String line = "";
        List<CityRouteTO> cityRouteTOList = new ArrayList<>();
        FileReader fileReader = getFileReader();

        try(BufferedReader reader = new BufferedReader(fileReader)) {
            while ((line = reader.readLine()) != null) {
                String[] routesPair = line.split(WORD_SEPARATOR);
                cityRouteTOList.add(getCityRoute(routesPair));
            }
        } catch (Exception e) {
            System.out.println(String.format("Exception occurred while parsing csv file"));
            e.printStackTrace();
        }
        return cityRouteTOList;
    }

    /**
     * Map the raw content obtained from file parsing to {@link CityRouteTO}
     * @param routesPair
     * @return
     */
    public CityRouteTO getCityRoute(String[] routesPair) {

        if(routesPair == null || routesPair.length < 2) {
            throw new InvalidRoutePair("Either empty or invalid src/dest pair received");
        }
        CityRouteTO route = new CityRouteTO();
        route.setSource(routesPair[0].trim());
        route.setDestination(routesPair[1].trim());
        return  route;
    }

    /**
     * Below method always expects file with name city.txt
     * on classpath
     * @return
     * @throws FileNotFoundException
     */
    public FileReader getFileReader() throws FileNotFoundException {
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource("city.txt");

        if(resource == null) throw new IllegalArgumentException("Provided file not found");
        return new FileReader(new File(resource.getFile()));
    }
}
