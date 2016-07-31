package com.prabhu.codepath.nytimessearch.decorators;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by pbabu on 7/29/16.
 */
public class ArticleItemDecoration extends RecyclerView.ItemDecoration {
    final private int space;

    public ArticleItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
    }
}
