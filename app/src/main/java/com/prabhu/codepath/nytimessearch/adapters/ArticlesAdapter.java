package com.prabhu.codepath.nytimessearch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prabhu.codepath.nytimessearch.R;
import com.prabhu.codepath.nytimessearch.models.Doc;
import com.prabhu.codepath.nytimessearch.models.Multimedia;
import com.prabhu.codepath.nytimessearch.viewHolders.ArticleViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pbabu on 7/29/16.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String THUMBNAIL_TYPE = "wide";
    private List<Doc> mArticles;
    private Context mContext;
    private OnItemClickListener mListener;
    public ArticlesAdapter(Context context, List<Doc> articles, OnItemClickListener listener) {
        this.mArticles = articles;
        this.mContext = context;
        this.mListener = listener;
    }


    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View itemView = layoutInflater.inflate(R.layout.list_item_article, parent, false);
        final ArticleViewHolder articleViewHolder = new ArticleViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    final int position = articleViewHolder.getLayoutPosition();
                    final Doc article = mArticles.get(position);
                    mListener.onItemClick(article);
                }
            }
        });
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Doc article = mArticles.get(position);
        final List<Multimedia> multimediaList = article.getMultimedia();
        Multimedia thumbnail = null;
        for(Multimedia multimedia: multimediaList) {
            if(multimedia.getSubtype().equals(THUMBNAIL_TYPE)){
                thumbnail = multimedia;
                break;
            }
        }
        ArticleViewHolder articleViewHolder = (ArticleViewHolder)holder;
        articleViewHolder.tvArticleHeadline.setText(article.getHeadline().getMain());
        if(thumbnail != null) {
            String imageUrl = String.format("http://www.nytimes.com/%s", thumbnail.getUrl());
            Picasso.with(mContext).load(imageUrl)
                    .into(articleViewHolder.ivArticleImage);
        }else {
            Picasso.with(mContext).load(R.drawable.image_placeholder)
                    .into(articleViewHolder.ivArticleImage);
        }

    }

    public void addAll(List<Doc> articles){
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }

    public void clear() {
        mArticles.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Doc article);
    }


}
