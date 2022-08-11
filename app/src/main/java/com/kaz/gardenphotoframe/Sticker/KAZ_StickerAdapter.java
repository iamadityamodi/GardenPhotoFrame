package com.kaz.gardenphotoframe.Sticker;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kaz.gardenphotoframe.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class KAZ_StickerAdapter extends BaseAdapter {

    ArrayList<String> stickerlist;
    Context mContext;

    public KAZ_StickerAdapter(ArrayList<String> stickerlist, Context mContext) {
        this.stickerlist = stickerlist;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return stickerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return stickerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        }

        viewHolder.imageview = convertView.findViewById(R.id.filterumage);
        AssetManager assetManager = mContext.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(stickerlist.get(position));
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            viewHolder.imageview.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    public class ViewHolder {
        ImageView imageview;

    }
}
