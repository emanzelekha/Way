package com.appytech.businessway.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appytech.businessway.R;

public class ItemPostViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout itemPostCardView;
    private ImageView itemPostUserPictureImageView;
    private TextView itemPostUsernameTextView;
    private TextView itemPostDateTextView;
    private ImageView itemPostMoreImageView;
    private TextView itemPostTitleTextView;
    private TextView itemPostBodyTextView;
    private CardView itemPostPictureLayout;
    private ImageView itemPostPictureImageView;
    private LinearLayout itemPostSharedLayout;
    private ImageView itemPostSharedUserPictureImageView;
    private TextView itemPostSharedUsernameTextView;
    private TextView itemPostSharedDateTextView;
    private TextView itemPostSharedTitleTextView;
    private TextView itemPostSharedBodyTextView;
    private CardView itemPostSharedPictureLayout;
    private ImageView itemPostSharedPictureImageView;
    private LinearLayout itemPostLikeLayout;
    private ImageView itemPostLikeImageView;
    private LinearLayout itemPostCommentLayout;
    private ImageView itemPostCommentImageView;
    private LinearLayout itemPostShareLayout;
    private ImageView itemPostShareImageView;

    public ItemPostViewHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.recycler_item_post, parent, false));
    }

    public ItemPostViewHolder(View view) {
        super(view);
        itemPostCardView = (LinearLayout) view.findViewById(R.id.item_post_cardView);
        itemPostUserPictureImageView = (ImageView) itemPostCardView.findViewById(R.id.item_post_user_picture_imageView);
        itemPostUsernameTextView = (TextView) itemPostCardView.findViewById(R.id.item_post_username_textView);
        itemPostDateTextView = (TextView) itemPostCardView.findViewById(R.id.item_post_date_textView);
        itemPostMoreImageView = (ImageView) itemPostCardView.findViewById(R.id.item_post_more_imageView);
        itemPostTitleTextView = (TextView) itemPostCardView.findViewById(R.id.item_post_title_textView);
        itemPostBodyTextView = (TextView) itemPostCardView.findViewById(R.id.item_post_body_textView);
        itemPostPictureLayout = (CardView) itemPostCardView.findViewById(R.id.item_post_picture_layout);
        itemPostPictureImageView = (ImageView) itemPostPictureLayout.findViewById(R.id.item_post_picture_imageView);
        itemPostSharedLayout = (LinearLayout) itemPostCardView.findViewById(R.id.item_post_shared_layout);
        itemPostSharedUserPictureImageView = (ImageView) itemPostSharedLayout.findViewById(R.id.item_post_shared_user_picture_imageView);
        itemPostSharedUsernameTextView = (TextView) itemPostSharedLayout.findViewById(R.id.item_post_shared_username_textView);
        itemPostSharedDateTextView = (TextView) itemPostSharedLayout.findViewById(R.id.item_post_shared_date_textView);
        itemPostSharedTitleTextView = (TextView) itemPostSharedLayout.findViewById(R.id.item_post_shared_title_textView);
        itemPostSharedBodyTextView = (TextView) itemPostSharedLayout.findViewById(R.id.item_post_shared_body_textView);
        itemPostSharedPictureLayout = (CardView) itemPostSharedLayout.findViewById(R.id.item_post_shared_picture_layout);
        itemPostSharedPictureImageView = (ImageView) itemPostSharedPictureLayout.findViewById(R.id.item_post_shared_picture_imageView);
        itemPostLikeLayout = (LinearLayout) itemPostCardView.findViewById(R.id.item_post_like_layout);
        itemPostLikeImageView = (ImageView) itemPostLikeLayout.findViewById(R.id.item_post_like_imageView);
        itemPostCommentLayout = (LinearLayout) itemPostCardView.findViewById(R.id.item_post_comment_layout);
        itemPostCommentImageView = (ImageView) itemPostCommentLayout.findViewById(R.id.item_post_comment_imageView);
        itemPostShareLayout = (LinearLayout) itemPostCardView.findViewById(R.id.item_post_share_layout);
        itemPostShareImageView = (ImageView) itemPostShareLayout.findViewById(R.id.item_post_share_imageView);
    }

    public ImageView getItemPostSharedUserPictureImageView() {
        return itemPostSharedUserPictureImageView;
    }

    public ImageView getItemPostLikeImageView() {
        return itemPostLikeImageView;
    }

    public TextView getItemPostBodyTextView() {
        return itemPostBodyTextView;
    }

    public TextView getItemPostTitleTextView() {
        return itemPostTitleTextView;
    }

    public LinearLayout getItemPostCardView() {
        return itemPostCardView;
    }

    public ImageView getItemPostPictureImageView() {
        return itemPostPictureImageView;
    }

    public LinearLayout getItemPostShareLayout() {
        return itemPostShareLayout;
    }

    public LinearLayout getItemPostCommentLayout() {
        return itemPostCommentLayout;
    }

    public TextView getItemPostDateTextView() {
        return itemPostDateTextView;
    }

    public TextView getItemPostSharedBodyTextView() {
        return itemPostSharedBodyTextView;
    }

    public ImageView getItemPostSharedPictureImageView() {
        return itemPostSharedPictureImageView;
    }

    public LinearLayout getItemPostLikeLayout() {
        return itemPostLikeLayout;
    }

    public ImageView getItemPostUserPictureImageView() {
        return itemPostUserPictureImageView;
    }

    public TextView getItemPostSharedTitleTextView() {
        return itemPostSharedTitleTextView;
    }

    public ImageView getItemPostShareImageView() {
        return itemPostShareImageView;
    }

    public CardView getItemPostSharedPictureLayout() {
        return itemPostSharedPictureLayout;
    }

    public TextView getItemPostSharedDateTextView() {
        return itemPostSharedDateTextView;
    }

    public LinearLayout getItemPostSharedLayout() {
        return itemPostSharedLayout;
    }

    public CardView getItemPostPictureLayout() {
        return itemPostPictureLayout;
    }

    public ImageView getItemPostMoreImageView() {
        return itemPostMoreImageView;
    }

    public TextView getItemPostSharedUsernameTextView() {
        return itemPostSharedUsernameTextView;
    }

    public ImageView getItemPostCommentImageView() {
        return itemPostCommentImageView;
    }

    public TextView getItemPostUsernameTextView() {
        return itemPostUsernameTextView;
    }
}
