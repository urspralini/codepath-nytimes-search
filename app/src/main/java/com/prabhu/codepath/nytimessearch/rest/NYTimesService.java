package com.prabhu.codepath.nytimessearch.rest;

import com.prabhu.codepath.nytimessearch.models.NYTimesArticleSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pbabu on 7/26/16.
 */
public interface NYTimesService {

    @GET("svc/search/v2/articlesearch.json?api-key=bc0a5b235d26478d9d66a4ece023928b&fl=web_url,multimedia,headline")
    Call<NYTimesArticleSearchResponse> getArticles(@Query("q") String query, @Query("page") int page);
}
