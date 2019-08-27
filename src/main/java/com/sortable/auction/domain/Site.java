package com.sortable.auction.domain;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Site {

    @JsonProperty("name")
    private String name;

    @JsonProperty("floor")
    private Double floor;

    @JsonProperty("bidders")
    private List<String> bidders = null;

    private HashMap<String, Double> authorizedBidders = new HashMap<String, Double>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFloor() {
        return floor;
    }

    public void setFloor(Double floor) {
        this.floor = floor;
    }

    public List<String> getBidders() {
        return bidders;
    }

    public void setBidders(List<String> bidders) {
        this.bidders = bidders;
    }

    public HashMap<String, Double> getAuthorizedBidders() {
        return this.authorizedBidders;
    }

    public void setAuthorizedBidders(HashMap<String, Double> authorizedBidders) {
        this.authorizedBidders = authorizedBidders;
    }

}
