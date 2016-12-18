package com.appytech.businessway.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.appytech.businessway.R;
import com.appytech.businessway.fragments.CardsFragment;
import com.appytech.businessway.fragments.HomeFragment;
import com.appytech.businessway.fragments.MenuFragment;
import com.appytech.businessway.fragments.NearbyFragment;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private JSONArray postsJsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        openFragment(HomeFragment.newInstance());
        findViewById(R.id.main_home_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHide(0);
                openFragment(HomeFragment.newInstance());
            }
        });
        findViewById(R.id.main_card_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHide(1);
                openFragment(CardsFragment.newInstance());
            }
        });
        findViewById(R.id.main_nearby_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHide(2);
                openFragment(NearbyFragment.newInstance());
            }
        });
        findViewById(R.id.main_more_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHide(3);
                openFragment(MenuFragment.newInstance());
            }
        });

    }

    private int[] imagesIDs={R.id.main_home_imageView, R.id.main_card_imageView, R.id.main_nearby_imageView, R.id.main_more_imageView},
            linesIDs={R.id.main_home_line_imageView, R.id.main_card_line_imageView, R.id.main_nearby_line_imageView, R.id.main_more_line_imageView},
            selectedImagesResources={R.drawable.icon_selected_home, R.drawable.icon_selected_card, R.drawable.icon_selected_nearby, R.drawable.ic_selected_menu},
            imagesResources={R.drawable.icon_home, R.drawable.icon_card, R.drawable.icon_nearby, R.drawable.ic_menu};

    private void onClickHide(int index){
        for (int i=0; i<imagesIDs.length;i++){
            if(i!=index){
                ((ImageView)findViewById(imagesIDs[i])).setImageResource(imagesResources[i]);
                ((ImageView)findViewById(linesIDs[i])).setImageResource(R.color.colorGrey);
            }else{
                ((ImageView)findViewById(imagesIDs[i])).setImageResource(selectedImagesResources[i]);
                ((ImageView)findViewById(linesIDs[i])).setImageResource(R.color.colorPrimary);
            }
        }
    }

    private void getPostsJSONArray() {
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

    private Class currentFragmentClass;
    private void openFragment(Fragment fragment){
        if(currentFragmentClass!=fragment.getClass()) {
            currentFragmentClass=fragment.getClass();
            Log.e("openFragment", fragment.getClass().getName() + "");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container_layout, fragment)
                    .commit();
        }
    }
}
