package com.sortable.auction.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {

    @JsonProperty("sites")
    private List<Site> sites = null;
    @JsonProperty("bidders")
    private List<Bidder> bidders = null;

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public List<Bidder> getBidders() {
        return bidders;
    }

    public void setBidders(List<Bidder> bidders) {
        this.bidders = bidders;
    }

}