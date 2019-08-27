package com.sortable.auction.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bid {

    @JsonProperty("bidder")
    private String bidder;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("bid")
    private Double bid;

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

}