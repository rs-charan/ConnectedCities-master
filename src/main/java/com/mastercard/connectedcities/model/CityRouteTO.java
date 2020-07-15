package com.mastercard.connectedcities.model;

/**
 * Object which represents edge or connection
 * between two cities
 */
public class CityRouteTO {

    private String source;
    private String destination;

    public String getSource() { return source; }

    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }

    public void setDestination(String destination) { this.destination = destination; }

}
