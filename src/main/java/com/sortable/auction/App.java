package com.sortable.auction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sortable.auction.service.AuctionService;

public class App {

    static Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        InputStream inputJSONStream = System.in;

        try {
            AuctionService auctionService = new AuctionService(new ObjectMapper());

            System.out.println(auctionService.processAuctionSessions(inputJSONStream));

        } catch (FileNotFoundException ex) {
            LOG.severe("File Not found : " + ex.getMessage());
        } catch (JsonParseException e) {
            LOG.severe("Json Parse Exception : " + e.getMessage());
        } catch (JsonMappingException e) {
            LOG.severe("Json Mapping Exception : " + e.getMessage());
        } catch (IOException e) {
            LOG.severe("IO Exception:  " + e.getMessage());
        }
    }
}