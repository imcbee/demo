package com.example.test.demo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    
    private final String IS_SITE_UP = "Site is Up!";
    private final String IS_SITE_DOWN = "Site is Down!";
    private final String INCORRECT_URL = "URL is incorrect!";

    
    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;

            if (responseCodeCategory == 2) {
                returnMessage = IS_SITE_UP;
            }else {
                returnMessage = IS_SITE_DOWN;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            returnMessage = INCORRECT_URL;
        } catch ( IOException e) {
            returnMessage = IS_SITE_DOWN;
        }
        
        return returnMessage;
    }


}
