package com.kaz.gardenphotoframe.Text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kaz.gardenphotoframe.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class KAZ_TextureAdapter extends BaseAdapter {
    ArrayList<String> textureList;
    Context mContext;

    public KAZ_TextureAdapter(ArrayList<String> textureList, Context mContext) {
        this.textureList = textureList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return textureList.size();
    }

    @Override
    public Object getItem(int position) {
        return textureList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.texture_item, null);
        }

        viewHolder.ivTextureItem = convertView.findViewById(R.id.cvTextureItem);
        AssetManager assetManager = mContext.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(textureList.get(position));
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            viewHolder.ivTextureItem.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    public class ViewHolder {
        CircleImageView ivTextureItem;
    }
}
