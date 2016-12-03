package com.appytech.businessway.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;

import com.appytech.businessway.R;
import com.appytech.businessway.adapters.RecyclerAdapter;
import com.appytech.businessway.adapters.ViewPagerAdapter;
import com.appytech.businessway.viewholders.ItemPostViewHolder;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RecyclerView postsRecyclerView;
    private RecyclerAdapter postsRecyclerAdapter;
    private JSONArray postsJsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
//        initTabs();
//        fillPostsList();
    }

    private void initTabs() {
        TabLayout tabLayout=(TabLayout)findViewById(R.id.main_tabLayout);
        ViewPager viewPager=(ViewPager) findViewById(R.id.main_viewPager);
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());
//        viewPagerAdapter.addTab();
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void fillPostsList() {
        postsRecyclerView = (RecyclerView) findViewById(R.id.main_posts_recyclerView);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postsRecyclerAdapter = new RecyclerAdapter(postsJsonArray, R.layout.recycler_item_post, ItemPostViewHolder.class, new RecyclerAdapter.AdapterInterface<JSONObject, ItemPostViewHolder>() {
            @Override
            public void fillData(JSONObject dataModel, ItemPostViewHolder viewHolder, int position) {

            }
        });
        postsRecyclerView.setAdapter(postsRecyclerAdapter);
    }

    private void getPosts() {
        postsJsonArray = new JSONArray();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.main_title_textView);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolBarTextView.setText(getString(R.string.app_name));
    }
}
