package com.kaz.gardenphotoframe.Save;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import com.kaz.gardenphotoframe.BuildConfig;
import com.kaz.gardenphotoframe.R;


public class KAZ_CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<String> spacecrafts;
    File file;

    public KAZ_CustomAdapter(Context c, ArrayList<String> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int i) {
        return spacecrafts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.model, viewGroup, false);
        }

        ImageView img = (ImageView) view.findViewById(R.id.spacecraftImg);
        ImageView sharebtn, deletebtn;
        sharebtn = view.findViewById(R.id.sharebtn);
        deletebtn = view.findViewById(R.id.deletebtn);

        Glide.with(c).load(spacecrafts.get(i)).into(img);

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedialog(i);
            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File imageFile = new File(spacecrafts.get(i));
                Uri uri = FileProvider.getUriForFile(c, BuildConfig.APPLICATION_ID + ".provider", imageFile);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                intent.setType("text/plain");
                intent.setType("image/png");
                c.startActivity(Intent.createChooser(intent, "Share Via"));

            }
        });
        return view;
    }

    private void deletedialog(int pos) {

        final Dialog myDialog = new Dialog(c);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setContentView(R.layout.delete_dialog);
        myDialog.setCancelable(false);

        Button yes = (Button) myDialog.findViewById(R.id.yes);
        Button no = (Button) myDialog.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                file = new File(spacecrafts.get(pos));
//                if (file.exists()) {
//                    if (file.delete()) {
//                        spacecrafts.remove(spacecrafts.get(pos));
//                        notifyDataSetChanged();
//                    }
//                }
//                Toast.makeText(c, "succesfully deleted", Toast.LENGTH_SHORT).show();


                File fD = new File(spacecrafts.get(pos));
                if (fD.exists()) {
                    fD.delete();
                }

                spacecrafts.remove(pos);
                Toast.makeText(c, "succesfully deleted", Toast.LENGTH_SHORT).show();

                notifyDataSetChanged();

                myDialog.dismiss();
            }
        });

        myDialog.show();
    }

}