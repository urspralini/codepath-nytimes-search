package com.prabhu.codepath.nytimessearch.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prabhu.codepath.nytimessearch.R;

/**
 * Created by pbabu on 7/29/16.
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivArticleImage;
    public TextView tvArticleHeadline;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        ivArticleImage = (ImageView)itemView.findViewById(R.id.ivArticleImage);
        tvArticleHeadline = (TextView)itemView.findViewById(R.id.tvArticleHeadline);
    }

}
