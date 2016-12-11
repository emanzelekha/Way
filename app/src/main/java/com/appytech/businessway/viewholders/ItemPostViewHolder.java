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
    private LinearLayout postBodyLayout;
    private ImageView itemPostUserPictureImageView;
    private TextView itemPostUsernameTextView;
    private TextView itemPostDateTextView;
    private ImageView itemPostMoreImageView;
    private TextView itemPostTitleTextView;
    private TextView itemPostBodyTextView;
    private CardView itemPostPictureLayout;
    private ImageView itemPostPictureImageView;
    private LinearLayout itemPostSharedLayout;
    private LinearLayout postSharedBodyLayout;
    private ImageView itemPostUserPictureImageView2;
    private TextView itemPostUsernameTextView2;
    private TextView itemPostDateTextView2;
    private ImageView itemPostMoreImageView2;
    private TextView itemPostTitleTextView2;
    private TextView itemPostBodyTextView2;
    private CardView itemPostPictureLayout2;
    private ImageView itemPostPictureImageView2;
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
        postBodyLayout = (LinearLayout) itemPostCardView.findViewById(R.id.post_body_layout);
        itemPostUserPictureImageView = (ImageView) postBodyLayout.findViewById(R.id.item_post_user_picture_imageView);
        itemPostUsernameTextView = (TextView) postBodyLayout.findViewById(R.id.item_post_username_textView);
        itemPostDateTextView = (TextView) postBodyLayout.findViewById(R.id.item_post_date_textView);
        itemPostMoreImageView = (ImageView) postBodyLayout.findViewById(R.id.item_post_more_imageView);
        itemPostTitleTextView = (TextView) postBodyLayout.findViewById(R.id.item_post_title_textView);
        itemPostBodyTextView = (TextView) postBodyLayout.findViewById(R.id.item_post_body_textView);
        itemPostPictureLayout = (CardView) postBodyLayout.findViewById(R.id.item_post_picture_layout);
        itemPostPictureImageView = (ImageView) itemPostPictureLayout.findViewById(R.id.item_post_picture_imageView);
        itemPostSharedLayout = (LinearLayout) itemPostCardView.findViewById(R.id.item_post_shared_layout);
        postSharedBodyLayout = (LinearLayout) itemPostSharedLayout.findViewById(R.id.post_shared_body_layout);
        itemPostUserPictureImageView2 = (ImageView) postSharedBodyLayout.findViewById(R.id.item_post_user_picture_imageView);
        itemPostUsernameTextView2 = (TextView) postSharedBodyLayout.findViewById(R.id.item_post_username_textView);
        itemPostDateTextView2 = (TextView) postSharedBodyLayout.findViewById(R.id.item_post_date_textView);
        itemPostMoreImageView2 = (ImageView) postSharedBodyLayout.findViewById(R.id.item_post_more_imageView);
        itemPostTitleTextView2 = (TextView) postSharedBodyLayout.findViewById(R.id.item_post_title_textView);
        itemPostBodyTextView2 = (TextView) postSharedBodyLayout.findViewById(R.id.item_post_body_textView);
        itemPostPictureLayout2 = (CardView) postSharedBodyLayout.findViewById(R.id.item_post_picture_layout);
        itemPostPictureImageView2 = (ImageView) itemPostPictureLayout2.findViewById(R.id.item_post_picture_imageView);
        itemPostLikeLayout = (LinearLayout) itemPostCardView.findViewById(R.id.item_post_like_layout);
        itemPostLikeImageView = (ImageView) itemPostLikeLayout.findViewById(R.id.item_post_like_imageView);
        itemPostCommentLayout = (LinearLayout) itemPostCardView.findViewById(R.id.item_post_comment_layout);
        itemPostCommentImageView = (ImageView) itemPostCommentLayout.findViewById(R.id.item_post_comment_imageView);
        itemPostShareLayout = (LinearLayout) itemPostCardView.findViewById(R.id.item_post_share_layout);
        itemPostShareImageView = (ImageView) itemPostShareLayout.findViewById(R.id.item_post_share_imageView);
    }

    public ImageView getItemPostPictureImageView2() {
        return itemPostPictureImageView2;
    }

    public ImageView getItemPostPictureImageView() {
        return itemPostPictureImageView;
    }

    public LinearLayout getItemPostCardView() {
        return itemPostCardView;
    }

    public TextView getItemPostBodyTextView2() {
        return itemPostBodyTextView2;
    }

    public ImageView getItemPostLikeImageView() {
        return itemPostLikeImageView;
    }

    public LinearLayout getItemPostCommentLayout() {
        return itemPostCommentLayout;
    }

    public LinearLayout getItemPostShareLayout() {
        return itemPostShareLayout;
    }

    public LinearLayout getPostBodyLayout() {
        return postBodyLayout;
    }

    public ImageView getItemPostMoreImageView() {
        return itemPostMoreImageView;
    }

    public LinearLayout getItemPostLikeLayout() {
        return itemPostLikeLayout;
    }

    public LinearLayout getItemPostSharedLayout() {
        return itemPostSharedLayout;
    }

    public CardView getItemPostPictureLayout2() {
        return itemPostPictureLayout2;
    }

    public ImageView getItemPostUserPictureImageView() {
        return itemPostUserPictureImageView;
    }

    public CardView getItemPostPictureLayout() {
        return itemPostPictureLayout;
    }

    public TextView getItemPostDateTextView2() {
        return itemPostDateTextView2;
    }

    public LinearLayout getPostSharedBodyLayout() {
        return postSharedBodyLayout;
    }

    public TextView getItemPostDateTextView() {
        return itemPostDateTextView;
    }

    public TextView getItemPostUsernameTextView2() {
        return itemPostUsernameTextView2;
    }

    public ImageView getItemPostShareImageView() {
        return itemPostShareImageView;
    }

    public TextView getItemPostUsernameTextView() {
        return itemPostUsernameTextView;
    }

    public TextView getItemPostTitleTextView2() {
        return itemPostTitleTextView2;
    }

    public ImageView getItemPostCommentImageView() {
        return itemPostCommentImageView;
    }

    public TextView getItemPostBodyTextView() {
        return itemPostBodyTextView;
    }

    public TextView getItemPostTitleTextView() {
        return itemPostTitleTextView;
    }

    public ImageView getItemPostMoreImageView2() {
        return itemPostMoreImageView2;
    }

    public ImageView getItemPostUserPictureImageView2() {
        return itemPostUserPictureImageView2;
    }
}
