package com.prabhu.codepath.nytimessearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.prabhu.codepath.nytimessearch.adapters.ArticlesAdapter;
import com.prabhu.codepath.nytimessearch.decorators.ArticleItemDecoration;
import com.prabhu.codepath.nytimessearch.models.Doc;
import com.prabhu.codepath.nytimessearch.models.Multimedia;
import com.prabhu.codepath.nytimessearch.models.NYTimesArticleSearchResponse;
import com.prabhu.codepath.nytimessearch.rest.NYTimesClient;
import com.prabhu.codepath.nytimessearch.rest.NYTimesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    public static final String THUMBNAIL_TYPE = "wide";
    private static final String LOG_TAG = SearchActivity.class.getSimpleName();
    private List<Doc> mArticles = new ArrayList<>();
    private ArticlesAdapter mArticlesAdapter;
    private int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final NYTimesService nyTimesService = NYTimesClient.getInstance()
                .getNytimesService();
        RecyclerView rvArticles = (RecyclerView)findViewById(R.id.rvArticles);
        rvArticles.setLayoutManager(new GridLayoutManager(this, 3));
        int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        ArticleItemDecoration itemDecoration = new ArticleItemDecoration(spaceInPixels);
        rvArticles.addItemDecoration(itemDecoration);
        mArticlesAdapter = new ArticlesAdapter(this, mArticles);
        rvArticles.setAdapter(mArticlesAdapter);
        final Call<NYTimesArticleSearchResponse> articlesSearchCall = nyTimesService.getArticles("Hillary Clinton", page);
        final Callback<NYTimesArticleSearchResponse> callback = new Callback<NYTimesArticleSearchResponse>() {
            @Override
            public void onResponse(Call<NYTimesArticleSearchResponse> call, Response<NYTimesArticleSearchResponse> response) {
                final List<Doc> articles = response.body().getResponse().getDocs();
                List<Doc> articlesWithImages = new ArrayList<>();
                for(Doc article : articles) {
                    if(hasThumbnailImage(article)) {
                        articlesWithImages.add(article);
                    }
                }
                mArticlesAdapter.addAll(articlesWithImages);
                if (mArticlesAdapter.getItemCount() <= 21) {
                    nyTimesService.getArticles("Hillary Clinton", ++page).enqueue(this);
                }
            }

            @Override
            public void onFailure(Call<NYTimesArticleSearchResponse> call, Throwable t) {
                Log.d(LOG_TAG, "results:" + t.getMessage());
            }
        }; articlesSearchCall.enqueue(callback);
    }

    private boolean hasThumbnailImage(Doc doc) {
        final List<Multimedia> multimediaList = doc.getMultimedia();
        if(multimediaList == null || multimediaList.isEmpty()) return false;
        for(Multimedia multimedia : multimediaList){
            if(multimedia.getSubtype().equals(THUMBNAIL_TYPE)) return true;
        }
        return false;
    }
}
