package com.appytech.businessway.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eng_m on 7/22/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

//    private HashMap<String, Fragment> fragmentHashMap;
//    private String[] titles;
    private List<String> titlesList;
    private List<Fragment> fragmentsList;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        titlesList=new ArrayList<>();
        fragmentsList=new ArrayList<>();
//        this.fragmentHashMap=fragmentHashMap;
//        titles=new String[fragmentHashMap.size()];
//        titles=fragmentHashMap.keySet().toArray(titles);
    }

    public void addTab(String title, Fragment fragment){
        titlesList.add(title);
        fragmentsList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get(position);
    }
}