package com.kaz.gardenphotoframe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kaz.gardenphotoframe.R;

public class KAZ_CustomAdapter extends BaseAdapter {
    Context context;
    int animals[];
    LayoutInflater inflter;

    public KAZ_CustomAdapter(Context applicationContext, int[] animals) {
        this.context = applicationContext;
        this.animals = animals;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return animals.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.frameimage, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(animals[i]);

        return view;
    }
}