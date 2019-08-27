package com.sortable.auction.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bidder {

    @JsonProperty("name")
    private String name;

    @JsonProperty("adjustment")
    private Double adjustment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(Double adjustment) {
        this.adjustment = adjustment;
    }

}