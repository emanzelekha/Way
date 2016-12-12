package com.appytech.businessway.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appytech.businessway.R;
import com.appytech.businessway.adapters.RecyclerAdapter;
import com.appytech.businessway.tools.DialogManager;
import com.appytech.businessway.tools.JSONHelper;
import com.appytech.businessway.tools.LogManager;
import com.appytech.businessway.viewholders.ItemPostViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {


    private RecyclerView postsRecyclerView;
    private RecyclerAdapter postsRecyclerAdapter;
    private JSONArray postsJsonArray;

    private String userPic = "USER_PIC", username = "USER_NAME", share = "SHARED", date = "DATE", title = "TITLE", body = "BODY", pic = "TYPE_TWO_PIC", sharedUserPic = "SHARED_USER_PIC",
            sharedUsername = "SHARED_USER_NAME", sharedDate = "SHARED_DATE", sharedTitle = "SHARED_TITLE", sharedBody = "SHARED_BODY", sharedPIC = "SHARED_PIC";


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.home_all_news_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.showToast(getActivity(), "All news");

            }
        });

        view.findViewById(R.id.home_recent_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.showToast(getActivity(), "Recents");
            }
        });

        postsRecyclerView = (RecyclerView) view.findViewById(R.id.main_posts_recyclerView);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initPostsList();
        fillPostsList();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initPostsList() {
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        postsJsonArray = new JSONArray();
        try {
            jsonObject1.put(userPic, "http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg");
            jsonObject1.put(username, "David Mark");
            jsonObject1.put(share, 0);
            jsonObject1.put(date, "Yesterday at 11:48 PM");
            jsonObject1.put(title, "New business on the way");
            jsonObject1.put(body, "gyhdfhgfhdfdyfvvjhvgvaghcvjsvzklgkfdhgdxc nkmjlhylukdhsfxc bnljh;ldsthzgf nhoyitfdtuxhcghv bnhjoyirfidtsxhgch vjbkhigotrydtjcg mvmhgvliukhv yfijcg jvgioufychfgjvhkyfutcgvkjgu,jjkgk,hgjdrxfcgvhghigufykcjvggjkfhgcvbljvbknl j,jkgyujdxhcvbgufyudshxcvbnkgkufydjx bjnb vvhfcxfgsrhxgfhc hgcjcjj");

            jsonObject2.put(userPic, "http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg");
            jsonObject2.put(username, "Jemmy Ternnar");
            jsonObject2.put(share, 0);
            jsonObject2.put(date, "Today at 10:00 PM");
            jsonObject2.put(title, "In the future");
            jsonObject2.put(body, "aaedzrstrdgccghf6yrtdthgchgcfdyfvvjhvgvaghcvjsvzkgyujdxhcvbgufyudshxcvbnkgkufydjx bjnb vvhfcxfgsrhxgfhc hgcjcjj");
            jsonObject2.put(pic, "http://www.rmg.co.uk/sites/default/files/styles/slider/public/The%20City%20and%20Thames%20View%20L8629-007_slider_slider.JPG?itok=xMIum7fp");

            jsonObject3.put(userPic, "http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg");
            jsonObject3.put(username, "Mohsen Ibrahim");
            jsonObject3.put(share, 1);
            jsonObject3.put(date, "Yesterday at 12:48 PM");
            jsonObject3.put(title, "Technology today");
            jsonObject3.put(body, "gyhdfhgfhdfdyfvvjhvgvaghcvjsvzkgyujdxhcvbgufyudshxcvbnkgkufydjx bjnb vvhfcxfgsrhxgfhc hgcjcjj");

            jsonObject3.put(sharedUserPic, "http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg");
            jsonObject3.put(sharedUsername, "Ahmed Saad");
            jsonObject3.put(sharedDate, "Yesterday at 10:15 AM");
            jsonObject3.put(sharedTitle, "How to create your own company");
            jsonObject3.put(sharedBody, "fhdfdyfvvjhvgvaghcvjsvzkgyujdxhcvbgufyudshxcvbnkgkufydjx bjnb vvhfcxfgsrhxgfhc hgcjcjj");

            jsonObject4.put(userPic, "http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg");
            jsonObject4.put(username, "Mohsen Ibrahim");
            jsonObject4.put(share, 1);
            jsonObject4.put(date, "Yesterday at 12:48 PM");
            jsonObject4.put(title, "");
            jsonObject4.put(body, "");

            jsonObject4.put(sharedUserPic, "http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg");
            jsonObject4.put(sharedUsername, "Ahmed Saad");
            jsonObject4.put(sharedDate, "Yesterday at 10:15 AM");
            jsonObject4.put(sharedTitle, "How to create your own company");
            jsonObject4.put(sharedBody, "fhdfdyfvvjhvgvaghcvjsvzkgyujdxhcvbgufyudshxcvbnkgkufydjx bjnb vvhfcxfgsrhxgfhc hgcjcjj");
            jsonObject4.put(sharedPIC, "");

            postsJsonArray.put(0, jsonObject1);
            postsJsonArray.put(1, jsonObject2);
            postsJsonArray.put(2, jsonObject3);
            postsJsonArray.put(3, jsonObject4);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void fillPostsList() {
        postsRecyclerAdapter = new RecyclerAdapter(postsJsonArray, R.layout.recycler_item_post, ItemPostViewHolder.class, new RecyclerAdapter.AdapterInterface<JSONObject, ItemPostViewHolder>() {
            @Override
            public void fillData(JSONObject dataJsonObject, ItemPostViewHolder viewHolder, int position) {
                viewHolder.getItemPostMoreImageView2().setVisibility(View.INVISIBLE);
                if (JSONHelper.getIntFromJSONObject(dataJsonObject, share) == 1) {
                    LogManager.e("SHaRE", dataJsonObject.toString());
                    viewHolder.getItemPostSharedLayout().setVisibility(View.VISIBLE);
                    viewHolder.getItemPostUsernameTextView2().setText(JSONHelper.getStringFromJSONObject(dataJsonObject, sharedUsername));
                    viewHolder.getItemPostTitleTextView2().setText(JSONHelper.getStringFromJSONObject(dataJsonObject, sharedTitle));
                    viewHolder.getItemPostDateTextView2().setText(JSONHelper.getStringFromJSONObject(dataJsonObject, sharedDate));
                    viewHolder.getItemPostBodyTextView2().setText(JSONHelper.getStringFromJSONObject(dataJsonObject, sharedBody));
//                    new ViewHelper(MainActivity.this).setImage(viewHolder.getItemPostSharedUserPictureImageView(), JSONHelper.getStringFromJSONObject(dataJsonObject, sharedUserPic));
                    viewHolder.getItemPostUserPictureImageView2().setImageResource(R.drawable.user_pic);
                }
                if (dataJsonObject.has(pic)) {
                    viewHolder.getItemPostPictureLayout().setVisibility(View.VISIBLE);
                    viewHolder.getItemPostPictureImageView().setImageResource(R.drawable.pic);
//                    new ViewHelper(MainActivity.this).setImage(viewHolder.getItemPostPictureImageView(), JSONHelper.getStringFromJSONObject(dataJsonObject, pic));
                }
                if (dataJsonObject.has(sharedPIC)) {
                    viewHolder.getItemPostPictureLayout2().setVisibility(View.VISIBLE);
                    viewHolder.getItemPostPictureImageView2().setImageResource(R.drawable.pic);
                }
                if (JSONHelper.getStringFromJSONObject(dataJsonObject, title).length() != 0 && JSONHelper.getStringFromJSONObject(dataJsonObject, body).length() != 0) {
                    viewHolder.getItemPostTitleTextView().setText(JSONHelper.getStringFromJSONObject(dataJsonObject, title));
                    viewHolder.getItemPostBodyTextView().setText(JSONHelper.getStringFromJSONObject(dataJsonObject, body));
//                    ViewHelper.makeTextViewResizable(viewHolder.getItemPostBodyTextView(),4,"View more",true);
                }
                viewHolder.getItemPostUsernameTextView().setText(JSONHelper.getStringFromJSONObject(dataJsonObject, username));
                viewHolder.getItemPostDateTextView().setText(JSONHelper.getStringFromJSONObject(dataJsonObject, date));
                viewHolder.getItemPostUserPictureImageView().setImageResource(R.drawable.user);
//                new ViewHelper(MainActivity.this).setImage(viewHolder.getItemPostUserPictureImageView(), JSONHelper.getStringFromJSONObject(dataJsonObject, userPic));
            }
        });
        postsRecyclerView.setAdapter(postsRecyclerAdapter);

    }
}
