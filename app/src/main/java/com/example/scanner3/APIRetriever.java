package com.example.scanner3;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIRetriever {

    Item item;

    public APIRetriever() {

    }

    public void retrieveData(String content, String format) {
        try {
            // Create a stream to the URL
            // Here is where I found the JSON streams: https://api.barcodelookup.com/v2/products?barcode=9780140157376&formatted=y&key=tk1qu2huudl9znmbr894o1cr9gc1yy
            String s = "https://api.barcodelookup.com/v2/products?barcode=722301926192&formatted=y&key=tk1qu2huudl9znmbr894o1cr9gc1yy";
            URL url = new URL(s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // Read all data from the website into a single string
            String line = "";
            String allLines = "";
            do {
                line = reader.readLine();
                if (line != null) {
                    allLines += line;
                }
            }
            while (line != null);

            // Create the EarthquakeList object from the JSON data
            Gson gson = new Gson();
            item = gson.fromJson(allLines, Item.class);
        }
        catch (MalformedURLException murle) {
            System.out.println(murle.getMessage());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public Item getProductName() {
        return item;
    }
}
