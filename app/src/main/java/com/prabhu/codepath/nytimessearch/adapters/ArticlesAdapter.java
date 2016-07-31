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
    public static final String MULTIMEDIA_WIDE = "wide";
    public static final int THUMBNAIL_WIDE_TARGET_WIDTH = 190;
    public static final int THUMBNAIL_WIDE_TARGET_HEIGHT = 126;
    public static final String MULTIMEDIA_XLARGE = "xlarge";
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
        Multimedia thumbnailWide = null;
        Multimedia xLarge = null;
        for(Multimedia multimedia: multimediaList) {
            if(multimedia.getSubtype().equals(MULTIMEDIA_WIDE)){
                thumbnailWide = multimedia;
            }else if (multimedia.getSubtype().equals(MULTIMEDIA_XLARGE)) {
                xLarge = multimedia;
            }
        }
        ArticleViewHolder articleViewHolder = (ArticleViewHolder)holder;
        articleViewHolder.tvArticleHeadline.setText(article.getHeadline().getMain());
        if(thumbnailWide != null || xLarge != null) {
            String imageUrl;
            int targetWidth;
            int targetHeight;
            if(thumbnailWide != null) {
                imageUrl = thumbnailWide.getUrl();
                targetHeight = thumbnailWide.getHeight();
                targetWidth = thumbnailWide.getWidth();
            }else {
                imageUrl = xLarge.getUrl();
                targetHeight = THUMBNAIL_WIDE_TARGET_HEIGHT;
                targetWidth = THUMBNAIL_WIDE_TARGET_WIDTH;

            }
            String imageFullUrl = String.format("http://www.nytimes.com/%s", imageUrl);
            Picasso.with(mContext).load(imageFullUrl)
                    .placeholder(R.drawable.placeholder)
                    .resize(targetWidth,
                            targetHeight)
                    .into(articleViewHolder.ivArticleImage);
        } else{
            Picasso.with(mContext).load(R.drawable.placeholder)
                    .resize(THUMBNAIL_WIDE_TARGET_WIDTH,
                            THUMBNAIL_WIDE_TARGET_HEIGHT)
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
