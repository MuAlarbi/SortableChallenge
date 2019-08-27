package com.sortable.auction.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sortable.auction.domain.AuctionSession;
import com.sortable.auction.domain.Bid;
import com.sortable.auction.domain.Bidder;
import com.sortable.auction.domain.Config;
import com.sortable.auction.domain.Site;

public class AuctionService {

    private HashMap<String, Site> siteConfigration = new HashMap<String, Site>();
    private HashMap<String, Bidder> bidderConfigration = new HashMap<String, Bidder>();
    private final ObjectMapper objectMapper;
    public static final String AUCTION_CONFIG_JSON_LOCATION = "../auction/config.json";

    public AuctionService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * THis method loads site and bidder configurations
     *
     * @param String the path of the configuration file The methods parses the json
     *               into the auctionConfiguration object using the Jacson Mapper
     *               For faster configration retreival, site and bidder
     *               configurations are stored in HashMap
     */
    public void loadConfigration(String configPath) throws JsonParseException, JsonMappingException, IOException {
        Config auctionConfiguration = objectMapper.readValue(new FileInputStream(configPath), Config.class);
        auctionConfiguration.getSites().forEach((site) -> {
            this.siteConfigration.put(site.getName(), site);
        });
        auctionConfiguration.getBidders().forEach((bidder) -> {
            this.bidderConfigration.put(bidder.getName(), bidder);
        });
    }

    /**
     * THis method loads auction sessions
     *
     * @param InputStream it reads the input json file as a stream of array of json
     *                    objects then and parses every object intp AuctionSession
     *                    object
     * @return List ofAuctionSession objects
     */

    public List<AuctionSession> loadAuctions(InputStream inputJSONStream)
            throws JsonParseException, JsonMappingException, IOException {
        return Arrays.asList(objectMapper.readValue(inputJSONStream, AuctionSession[].class));
    }

    /**
     * THis method performs a check on whether an auction session is valid.
     *
     * @param Stirng domainName. If the domainName is found in the site
     *               configuration map, the method returns true, otherwise it
     *               returns false
     * @return Boolean siteConfigration.containsKey check result
     */
    public Boolean isRecongizedSite(String domainAddress) {
        return siteConfigration.containsKey(domainAddress);
    }

    /**
     * This method performs a check on whether an a bidder is authorized to bid on a
     * given site
     *
     * @param Stirng if bidderName is found in the site's list of authorized
     *               bidders, the method returns true, otherwise it returns false
     * @return Boolean site bidders' list check result
     */
    public Boolean isAuthorizedBidder(String auctionName, String bidderName) {
        return this.siteConfigration.get(auctionName).getBidders().contains(bidderName);
    }

    /**
     * This method prcess a bid on a given auction unit. First, it checks the
     * validatiy of the bid using this assumption: (bid mount + bidder adjutment) >
     * auction floor if the adjustedBid is valid, we check if its value is bigger
     * than the unit's winner's bid, if so the bid is placed as the acutions unit
     * winner
     * 
     * @param AuctionSession auctions.
     * @param Bid            bid
     * @output auction list of winners is added to the auctionService list of
     *         auctions' winners if the auction is in valid, an empty array of
     *         winners is added ot the auctionService winners
     */
    public void processBid(AuctionSession auction, Bid bid) {
        Double adjustedBid = bid.getBid() + bidderConfigration.get(bid.getBidder()).getAdjustment();
        Double auctionFloor = siteConfigration.get(auction.getSite()).getFloor();
        if (adjustedBid > auctionFloor) {
            if ((auction.getWinners().get(bid.getUnit()) == null)
                    || auction.getWinners().get(bid.getUnit()).getBid() < adjustedBid) {
                auction.getWinners().put(bid.getUnit(), bid);
            }
        }
    }

    /**
     * getWinners loads site and bidder configurations then it loads the input json
     * stream of acutions. For each auction, it reports the winners, if the auction
     * is in valid, an empty array is added
     * 
     * @param InputStream inputJSONStream.
     * @param Bid         bid
     * @return List<List<Bid>> auction sessions list of winners
     */
    public List<List<Bid>> getWinners(InputStream inputJSONStream) throws IOException {
        List<List<Bid>> winners = new ArrayList<List<Bid>>();
        this.loadConfigration(AUCTION_CONFIG_JSON_LOCATION);
        this.loadAuctions(inputJSONStream).forEach((auction) -> {
            if (this.isRecongizedSite(auction.getSite())) {
                auction.getBids().forEach((bid) -> {
                    if (this.isAuthorizedBidder(auction.getSite(), bid.getBidder())) {
                        if (auction.getUnits().contains(bid.getUnit()))
                            processBid(auction, bid);
                    }
                });
            }
            winners.add(auction.getWinners().values().stream().collect(Collectors.toList()));
        });
        return winners;
    }

    /**
     * processAuctionSessions receives inputJSONStream and passes it to getWinners.
     * then it uses
     * objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString() to create
     * a JSON string of the auction winnsers
     * 
     * @param InputStream inputJSONStream.
     * @return String auction sessions list of winners
     */
    public String processAuctionSessions(InputStream inputJSONStream) throws IOException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.getWinners(inputJSONStream));
    }
}