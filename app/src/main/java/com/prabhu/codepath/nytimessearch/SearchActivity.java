package com.prabhu.codepath.nytimessearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.prabhu.codepath.nytimessearch.models.NYTimesArticleSearchResponse;
import com.prabhu.codepath.nytimessearch.rest.NYTimesClient;
import com.prabhu.codepath.nytimessearch.rest.NYTimesService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final String LOG_TAG = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        NYTimesService nyTimesService = NYTimesClient.getInstance()
                .getNytimesService();
        final Call<NYTimesArticleSearchResponse> articlesSearchCall = nyTimesService.getArticles("android");
        articlesSearchCall.enqueue(new Callback<NYTimesArticleSearchResponse>() {
            @Override
            public void onResponse(Call<NYTimesArticleSearchResponse> call, Response<NYTimesArticleSearchResponse> response) {
                Log.d(LOG_TAG, "results:" + response.body().toString());
            }

            @Override
            public void onFailure(Call<NYTimesArticleSearchResponse> call, Throwable t) {
                Log.d(LOG_TAG, "results:" + t.getMessage());
            }
        });
    }
}
