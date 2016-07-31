package com.prabhu.codepath.nytimessearch.activities;

import android.content.Intent;
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
import android.widget.ProgressBar;

import com.prabhu.codepath.nytimessearch.R;
import com.prabhu.codepath.nytimessearch.adapters.ArticlesAdapter;
import com.prabhu.codepath.nytimessearch.decorators.ArticleItemDecoration;
import com.prabhu.codepath.nytimessearch.listeners.EndlessRecyclerViewScrollListener;
import com.prabhu.codepath.nytimessearch.models.Doc;
import com.prabhu.codepath.nytimessearch.models.FilterOptions;
import com.prabhu.codepath.nytimessearch.models.NYTimesArticleSearchResponse;
import com.prabhu.codepath.nytimessearch.rest.NYTimesClient;
import com.prabhu.codepath.nytimessearch.rest.NYTimesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesListActivity extends AppCompatActivity implements ArticlesAdapter.OnItemClickListener{
    public static final String THUMBNAIL_TYPE = "wide";
    private static final String LOG_TAG = ArticlesListActivity.class.getSimpleName();
    public static final int SPAN_COUNT = 3;
    private List<Doc> mArticles = new ArrayList<>();
    private ArticlesAdapter mArticlesAdapter;
    private final NYTimesService nyTimesService = NYTimesClient.getInstance()
            .getNytimesService();
    public static int OPTIONS_REQUEST_CODE = 1000;
    private FilterOptions mFilterOptions = new FilterOptions();
    private String mQuery = "";
    private EditText mEtSearchText;
    private MenuItem miProgressBarItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView rvArticles = (RecyclerView)findViewById(R.id.rvArticles);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        rvArticles.setLayoutManager(gridLayoutManager);
        int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        ArticleItemDecoration itemDecoration = new ArticleItemDecoration(spaceInPixels);
        rvArticles.addItemDecoration(itemDecoration);
        mArticlesAdapter = new ArticlesAdapter(this, mArticles, this);
        rvArticles.setAdapter(mArticlesAdapter);
        rvArticles.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                fetchArticles(page);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_articles_list, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        int searchEditId = android.support.v7.appcompat.R.id.search_src_text;
        mEtSearchText = (EditText)searchView.findViewById(searchEditId);
        mEtSearchText.setTextColor(Color.WHITE);
        mEtSearchText.setHintTextColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mArticlesAdapter.clear();
                mQuery = query;
                fetchArticles(0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        miProgressBarItem = menu.findItem(R.id.miActionProgress);
        final ProgressBar v = (ProgressBar)MenuItemCompat.getActionView(miProgressBarItem);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if(itemId == R.id.action_filter) {
            Intent filtersIntent = new Intent(this, SearchFiltersActivity.class);
            filtersIntent.putExtra(SearchFiltersActivity.FILTER_OPTIONS_KEY, mFilterOptions);
            startActivityForResult(filtersIntent, OPTIONS_REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == OPTIONS_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                mFilterOptions = data.getParcelableExtra(SearchFiltersActivity.FILTER_OPTIONS_KEY);
                mArticlesAdapter.clear();
                fetchArticles(0);
            }
        }
    }

    private void fetchArticles(final int page) {
        showProgressBar();
        String beginDate = mFilterOptions.getDateWithoutSeparator();
        String sortOrder = mFilterOptions.getSortOrder() != null ?
                mFilterOptions.getSortOrder().name().toLowerCase() : null;
        final String newDeskValues = mFilterOptions.getNewDeskValues();
        String fq = newDeskValues != null ?
                String.format("news_desk:(%s)", newDeskValues):null;
        if(!mQuery.isEmpty()){
            final Call<NYTimesArticleSearchResponse> articlesSearchCall = nyTimesService.getArticles(mQuery, page,
                    beginDate,sortOrder,fq);
            final Callback<NYTimesArticleSearchResponse> callback = new Callback<NYTimesArticleSearchResponse>() {
                @Override
                public void onResponse(Call<NYTimesArticleSearchResponse> call, Response<NYTimesArticleSearchResponse> response) {
                    hideProgressBar();
                    if(response != null && response.body()!= null) {
                        final List<Doc> articles = response.body().getResponse().getDocs();
                        mArticlesAdapter.addAll(articles);
                    }
                }

                @Override
                public void onFailure(Call<NYTimesArticleSearchResponse> call, Throwable t) {
                    Log.d(LOG_TAG, "results:" + t.getMessage());
                }
            };
            articlesSearchCall.enqueue(callback);
        }
    }

    @Override
    public void onItemClick(Doc article) {
        Intent browserIntent = new Intent(this, WebViewActivity.class);
        browserIntent.putExtra(WebViewActivity.URL_KEY, article.getWebUrl());
        startActivity(browserIntent);
    }

    private void showProgressBar() {
        miProgressBarItem.setVisible(true);
    }

    private void hideProgressBar() {
        miProgressBarItem.setVisible(false);
    }
}
