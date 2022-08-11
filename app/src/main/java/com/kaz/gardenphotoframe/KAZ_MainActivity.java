package com.kaz.gardenphotoframe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class KAZ_MainActivity extends AppCompatActivity {

    Button gallery, camera;

    int SELECT_PICTURE = 200;
    private static final int pic_id = 123;
    ImageView click_image_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gallery = findViewById(R.id.button);
        camera = findViewById(R.id.camera);
        click_image_id = (ImageView)findViewById(R.id.click_image);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagecapture();
            }
        });
    }

    private void imagecapture() {
        Intent camera_intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, pic_id);
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                Uri selectedImageUri = data.getData();
                Intent intent = new Intent(this, KAZ_EditActivity.class);
                intent.putExtra("imageUri", selectedImageUri.toString());
                startActivity(intent);
            }

            if (requestCode == pic_id) {

                Bitmap photo = (Bitmap)data.getExtras().get("data");
                click_image_id.setImageBitmap(photo);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}