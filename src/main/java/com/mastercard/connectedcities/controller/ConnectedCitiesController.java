package com.mastercard.connectedcities.controller;

import com.mastercard.connectedcities.service.RouteFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Below class route incoming http request to appropriate
 * endpoint based on request received using {@link RestController}
 * and {@link org.springframework.web.bind.annotation.RequestMapping}
 *
 */
@RestController
public class ConnectedCitiesController {

    private RouteFinderService routeFinderService;
    private static final String YES = "yes";
    private static final String NO = "no";

    @Autowired
    public ConnectedCitiesController(RouteFinderService routeFinderService) {
        this.routeFinderService = routeFinderService;
    }

    /**
     * Below method routes request to {@link com.mastercard.connectedcities.service.RouteFinderService} and returns
     * yes/no
     * @param origin
     * @param destination
     * @return
     */
    @RequestMapping(path = "/connected", method = RequestMethod.GET)
    public String checkCitiesAreConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        boolean routeExists = routeFinderService.findSourceAndDestinationAreConnected(origin, destination);
        return routeExists ? YES : NO;
    }
}
