package com.prabhu.codepath.nytimessearch.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.prabhu.codepath.nytimessearch.R;
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

public class ArticlesListActivity extends AppCompatActivity {
    public static final String THUMBNAIL_TYPE = "wide";
    private static final String LOG_TAG = ArticlesListActivity.class.getSimpleName();
    private List<Doc> mArticles = new ArrayList<>();
    private ArticlesAdapter mArticlesAdapter;
    private final NYTimesService nyTimesService = NYTimesClient.getInstance()
            .getNytimesService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView rvArticles = (RecyclerView)findViewById(R.id.rvArticles);
        rvArticles.setLayoutManager(new GridLayoutManager(this, 3));
        int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        ArticleItemDecoration itemDecoration = new ArticleItemDecoration(spaceInPixels);
        rvArticles.addItemDecoration(itemDecoration);
        mArticlesAdapter = new ArticlesAdapter(this, mArticles);
        rvArticles.setAdapter(mArticlesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        int searchEditId = android.support.v7.appcompat.R.id.search_src_text;
        final EditText editText = (EditText)searchView.findViewById(searchEditId);
        editText.setTextColor(Color.WHITE);
        editText.setHintTextColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mArticlesAdapter.clear();
                fetchArticles(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private boolean hasThumbnailImage(Doc doc) {
        final List<Multimedia> multimediaList = doc.getMultimedia();
        if(multimediaList == null || multimediaList.isEmpty()) return false;
        for(Multimedia multimedia : multimediaList){
            if(multimedia.getSubtype().equals(THUMBNAIL_TYPE)) return true;
        }
        return false;
    }


    private void fetchArticles(final String query, final int page) {
        final Call<NYTimesArticleSearchResponse> articlesSearchCall = nyTimesService.getArticles(query, page);
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
                    fetchArticles(query, page+1);
                }
            }

            @Override
            public void onFailure(Call<NYTimesArticleSearchResponse> call, Throwable t) {
                Log.d(LOG_TAG, "results:" + t.getMessage());
            }
        }; articlesSearchCall.enqueue(callback);
    }
}
