package com.prabhu.codepath.nytimessearch.rest;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by pbabu on 7/26/16.
 */
public class NYTimesClient {

    private final static String BASE_URL= "https://api.nytimes.com/";
    private final static NYTimesClient INSTANCE = new NYTimesClient();
    private NYTimesService nytimesService;
    private NYTimesClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        nytimesService = retrofit.create(NYTimesService.class);
    }

    public static NYTimesClient getInstance() {
        return INSTANCE;
    }

    public NYTimesService getNytimesService() {
        return nytimesService;
    }
}
