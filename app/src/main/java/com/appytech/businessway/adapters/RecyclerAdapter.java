package com.appytech.businessway.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MH on 7/19/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List dataList;
    private int layout;
    private AdapterInterface adapterInterface;
    private Class<RecyclerView.ViewHolder> viewHolderClass;

    public RecyclerAdapter(JSONArray dataJsonArray, int layout, Class viewHolderClass, AdapterInterface adapterInterface) {
        this(convertJSONArrayToList(dataJsonArray), layout, viewHolderClass, adapterInterface);
    }
    public RecyclerAdapter(List dataList, int layout, Class viewHolderClass, AdapterInterface adapterInterface) {
        this.dataList=dataList;
        this.layout=layout;
        this.viewHolderClass=viewHolderClass;
        this.adapterInterface=adapterInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        RecyclerView.ViewHolder viewHolder= null;
        try {
            viewHolder = viewHolderClass.getConstructor(View.class).newInstance(v);
        } catch (Exception e) {}
//        RecyclerView.ViewHolder viewHolder = adapterInterface.initViewHolder(v);
        return viewHolder;
    }

    private Object getItem(int position){
        return dataList.get(position);
    }

    public void clearAllItems(){
        if(dataList!=null){
            dataList.clear();
            notifyDataSetChanged();
        }
    }

    public void addItem(Object item){
        if(dataList==null)dataList=new ArrayList<>();
        dataList.add(item);
        notifyDataSetChanged();
    }

    public void setData(JSONArray dataJSONArray){
        setData(convertJSONArrayToList(dataJSONArray));
    }
    public void setData(List dataList){
        this.dataList=dataList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        adapterInterface.fillData(getItem(position), viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface AdapterInterface<M, H extends RecyclerView.ViewHolder>{
//        H initViewHolder(View view);
        void fillData(M dataModel, H viewHolder, int position);
    }

    public static List convertJSONArrayToList(JSONArray jsonArray){
        List list=new ArrayList();
        for (int i=0; i<jsonArray.length(); i++){
            try {
                list.add(jsonArray.get(i));
            } catch (JSONException e) {}
        }
        return list;
    }
}
