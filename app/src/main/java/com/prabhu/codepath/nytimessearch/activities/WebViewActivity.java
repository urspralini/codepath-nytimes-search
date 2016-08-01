package com.prabhu.codepath.nytimessearch.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.prabhu.codepath.nytimessearch.R;
import com.prabhu.codepath.nytimessearch.utils.Helper;

public class WebViewActivity extends AppCompatActivity {

    public static final String URL_KEY = "url";
    private ShareActionProvider miShareAction;
    private WebView mWebView;
    private RelativeLayout mRlWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mRlWebView = (RelativeLayout)findViewById(R.id.rlWebView);
        mWebView = (WebView)findViewById(R.id.webview);
        configureWebView(mWebView);
        String url = getIntent().getStringExtra(URL_KEY);
        if(!Helper.isNetworkAvailable(this) || !Helper.isOnline()){
            Helper.showSnackBar(mRlWebView, this);
        }else if(url != null && !url.isEmpty()) {
            mWebView.loadUrl(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mWebView.getUrl());
        miShareAction.setShareIntent(shareIntent);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void configureWebView(WebView webView){
        // Configure related browser settings
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        webView.setWebViewClient(new MyBrowser());
    }

    private class MyBrowser extends WebViewClient {

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}
