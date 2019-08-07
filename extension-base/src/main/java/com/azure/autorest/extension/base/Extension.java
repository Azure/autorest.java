package com.azure.autorest.extension.base;

import com.azure.autorest.extension.base.implementation.ExtensionServiceImpl;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

import java.io.IOException;
import java.util.List;

public abstract class Extension {
    public abstract List<String> getPluginNames();

    public void run() {
        JSONRPC2Request reqIn = null;

        try {
            reqIn = JSONRPC2Request.parse();

        } catch (JSONRPC2ParseException e) {
            System.out.println(e.getMessage());
            // Handle exception...
        }

// How to extract the request data
        System.out.println("Parsed request with properties :");
        System.out.println("\tmethod     : " + reqIn.getMethod());
        System.out.println("\tparameters : " + reqIn.getNamedParams());
        System.out.println("\tid         : " + reqIn.getID() + "\n\n");

// Process request...

// Create the response (note that the JSON-RPC ID must be echoed back)
        String result = "payment-id-001";
        JSONRPC2Response respOut = new JSONRPC2Response(result, reqIn.getID());

// Serialise response to JSON-encoded string
        jsonString = respOut.toString();
    }
}
