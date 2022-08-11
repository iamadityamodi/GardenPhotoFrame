package com.kaz.gardenphotoframe.Text;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kaz.gardenphotoframe.R;

import java.util.ArrayList;


public class KAZ_FontListAdapter extends BaseAdapter {
    ArrayList<String> fontList;
    Context mContext;

    public KAZ_FontListAdapter(ArrayList<String> fontList, Context mContext) {
        this.fontList = fontList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return fontList.size();
    }

    @Override
    public Object getItem(int position) {
        return fontList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.font_item, null);
        }

        viewHolder.tvFontListItem = convertView.findViewById(R.id.tvFontListItem);
        viewHolder.tvFontListItem.setTypeface(Typeface.createFromAsset(mContext.getAssets(), fontList.get(position)));
        return convertView;
    }

    private class ViewHolder {
        TextView tvFontListItem;
    }
}
