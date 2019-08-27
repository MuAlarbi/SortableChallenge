package com.sortable.auction.domain;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuctionSession {

    @JsonProperty("site")
    private String site;

    @JsonProperty("units")
    private List<String> units = null;

    @JsonProperty("bids")
    private List<Bid> bids = null;

    @JsonIgnore
    private Bid winner;

    @JsonIgnore
    private HashMap<String, Bid> winners = new HashMap<String, Bid>();

    public AuctionSession site(String site) {
        this.site = site;
        return this;
    }

    public AuctionSession units(List<String> units) {
        this.units = units;
        return this;
    }

    public AuctionSession bids(List<Bid> bids) {
        this.bids = bids;
        return this;
    }

    public HashMap<String, Bid> getWinners() {
        return this.winners;
    }

    public void setWinners(HashMap<String, Bid> winners) {
        this.winners = winners;
    }

    public Bid getWinner() {
        return this.winner;
    }

    public void setWinner(Bid winner) {
        this.winner = winner;
    }

    public AuctionSession winner(Bid winner) {
        this.winner = winner;
        return this;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

}