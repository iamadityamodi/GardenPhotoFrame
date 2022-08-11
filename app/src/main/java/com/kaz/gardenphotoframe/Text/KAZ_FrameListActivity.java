package com.kaz.gardenphotoframe.Text;

import static androidx.core.content.FileProvider.getUriForFile;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import com.kaz.gardenphotoframe.Splash_Exit.KAZ_SplashActivity;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.kaz.gardenphotoframe.KAZ_CustomAdapter;
import com.kaz.gardenphotoframe.KAZ_EditActivity;
import com.kaz.gardenphotoframe.KAZ_HomeActivity;
import com.kaz.gardenphotoframe.R;

public class KAZ_FrameListActivity extends AppCompatActivity {

    GridView simpleGrid;
    int SELECT_PICTURE = 200;
    private static final int pic_id = 123;
    int  animals[] = {R.drawable.peacock_frame_1, R.drawable.peacock_frame_2, R.drawable.peacock_frame_3, R.drawable.peacock_frame_4, R.drawable.peacock_frame_5, R.drawable.peacock_frame_6, R.drawable.peacock_frame_7, R.drawable.peacock_frame_8, R.drawable.peacock_frame_9, R.drawable.peacock_frame_10, R.drawable.peacock_frame_11, R.drawable.peacock_frame_12};
    String cameraPermission[];
    String storagePermission[];
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    int position1;
    TextView textshader;
    File file;
    Uri uri;
    public static String fileName;
    static final int REQUEST_IMAGE_CAPTURE = 0;
    public static final int REQUEST_GALLERY_IMAGE = 1;
    private int requestMode = 1;
    private static final String SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage";
    private static final int REQUEST_SELECT_PICTURE_FOR_FRAGMENT = 0x02;
    public static Uri resultUri;
    public static int frameposition;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_list);



        simpleGrid = (GridView) findViewById(R.id.simpleGridView);
        textshader = findViewById(R.id.textshader);

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        KAZ_CustomAdapter KAZCustomAdapter = new KAZ_CustomAdapter(KAZ_FrameListActivity.this, animals);
        simpleGrid.setAdapter(KAZCustomAdapter);


        TextPaint paint = textshader.getPaint();
        float width = paint.measureText("Tianjin, China");

        Shader textShader = new LinearGradient(0, 0, width, textshader.getTextSize(),
                new int[]{
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#6944f8"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#6944f8"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#6944f8"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#6944f8"),
                        Color.parseColor("#f23d85"),
                }, null, Shader.TileMode.MIRROR);
        textshader.getPaint().setShader(textShader);


        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showImagePicDialog();
                position1 = position;

            }
        });
    }

    private void showImagePicDialog() {

        final Dialog myDialog = new Dialog(KAZ_FrameListActivity.this);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        myDialog.setContentView(R.layout.selectimagedialog);
        myDialog.setCancelable(false);

        LinearLayout yes = (LinearLayout) myDialog.findViewById(R.id.yes);
        LinearLayout no = (LinearLayout) myDialog.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkCameraPermission()) {
                    requestCameraPermission();
                } else {
                    Cameraopen();
                }
                myDialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkStoragePermission()) {
                    requestStoragePermission();
                } else {
                    pickFromGallery();
                }
                myDialog.dismiss();
            }
        });

        myDialog.show();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (resultCode == RESULT_OK) {
//            if (requestCode == SELECT_PICTURE) {
//
//                Uri sourceUri = data.getData();
////                File file = null;
//
//                file = getImageFile();
//                Uri destinationUri = Uri.fromFile(file);
//                openCropActivity(sourceUri, destinationUri);
//            }
//
//            if (requestCode == pic_id && resultCode == RESULT_OK) {
//                Uri uri = Uri.parse(currentPhotoPath);
//                openCropActivity(uri, uri);
//
//            } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
//
//                uri = UCrop.getOutput(data);
//                showImage(uri);
//            }
//        }

        switch (requestCode) {

            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    startCrop(getCacheImagePath(fileName));
                }
                else {
//                    handleCropError(data);
                }
                break;

            case REQUEST_GALLERY_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();
                    startCrop(imageUri);
                }
                else {
//                    handleCropError(data);
                }
                break;

            case UCrop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    handleCropResult(data);
                } else {
//                    handleCropError(data);
                }
                break;

            case UCrop.RESULT_ERROR:
                final Throwable cropError = UCrop.getError(data);
                Log.e("Crop error", "Crop error: " + cropError);
//                handleCropError(data);
                break;

            default:
                handleCropError(data);

        }
    }

    private void handleCropResult(@NonNull Intent result) {
        resultUri = UCrop.getOutput(result);
        if (resultUri != null) {

            Intent intent = new Intent(KAZ_FrameListActivity.this, KAZ_EditActivity.class);
            intent.setData(resultUri);
            intent.putExtra("frame",animals);
            intent.putExtra("image", animals[position1]);
            startActivity(intent);
            KAZ_SplashActivity.showAdmobInterstitial(this);
            Log.e("array","arrayposition="+frameposition);

        } else {
            Toast.makeText(KAZ_FrameListActivity.this, "toast_cannot_retrieve_cropped_image", Toast.LENGTH_SHORT).show();
        }
    }
    private void handleCropError(@NonNull Intent result)
    {

    }
    private void startCrop(@NonNull Uri uri) {

        String destinationFileName = SAMPLE_CROPPED_IMAGE_NAME;
        destinationFileName += ".png";

        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));

        uCrop = basisConfig(uCrop);
        uCrop = advancedConfig(uCrop);

        if (requestMode == REQUEST_SELECT_PICTURE_FOR_FRAGMENT) {
            Log.e("SUCCESS","sUccess");
        } else {                                                        // else start uCrop Activity
            uCrop.start(KAZ_FrameListActivity.this);
            Log.e("ERROR","error");
        }

    }

    private UCrop basisConfig(@NonNull UCrop uCrop) {
        uCrop = uCrop.withAspectRatio(0, 0);

        return uCrop;
    }


    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        UCrop.Options options = new UCrop.Options();

        options.setCompressionFormat(Bitmap.CompressFormat.PNG);

        return uCrop.withOptions(options);
    }


    private void showImage(Uri imageUri) {
        File file = new File(FileUtils.getPath(KAZ_FrameListActivity.this, imageUri));
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        Intent intent = new Intent(KAZ_FrameListActivity.this, KAZ_EditActivity.class);
        intent.putExtra("BitmapImage", bitmap);
        intent.putExtra("image", animals[position1]);
        startActivity(intent);

    }

//    private int IMAGE_COMPRESSION = 80;

    private void openCropActivity(Uri sourceUri, Uri destinationUri) {

        UCrop.of(sourceUri, destinationUri)
                .withMaxResultSize(100, 150)
                .withAspectRatio(5f, 5f)
                .start(KAZ_FrameListActivity.this);
    }

    private void Cameraopen() {

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            fileName = System.currentTimeMillis() + ".jpg";
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, getCacheImagePath(fileName));
                            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                            }
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
//        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        File file = null;
//            file = getImageFile();
//        Uri uri;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//            uri = FileProvider.getUriForFile(FrameListActivity.this, "com.kaz.gardenphotoframe.provider", file);
//        else
//            uri = Uri.fromFile(file);
//        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        startActivityForResult(camera_intent, pic_id);
    }


//    public String getImageFile(Uri uri) {
//        if (uri == null) {
//            return null;
//        }
//        String[] projection = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//        if (cursor != null) {
//            int column_index = cursor
//                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        }
//        return uri.getPath();
//    }

    private Uri getCacheImagePath(String fileName) {
        File path = new File(getExternalCacheDir(), "camera");
        if (!path.exists()) path.mkdirs();
        File image = new File(path, fileName);
        return getUriForFile(KAZ_FrameListActivity.this, getPackageName() + ".provider", image);
    }
    String currentPhotoPath = "";

    private File getImageFile() {

        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + "Photo_frame");
        file.mkdirs();
            String str = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpeg";
        File file2 = new File(file, str);
        file2.renameTo(file2);
        try {
            file = File.createTempFile(imageFileName, ".jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        currentPhotoPath = "file:" + file.getAbsolutePath();
//        return file;
        currentPhotoPath = "file:" + file;
        return file;
    }


    private void pickFromGallery() {

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override

                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, REQUEST_GALLERY_IMAGE);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();


//        Intent pictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        pictureIntent.setType("image/*");
//        pictureIntent.addCategory(Intent.CATEGORY_OPENABLE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            String[] mimeTypes = new String[]{"image/jpeg", "image/png", "image/jpg"};
//            pictureIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//        }
//        startActivityForResult(Intent.createChooser(pictureIntent, "Select Picture"), SELECT_PICTURE);

    }

    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    // Requesting  gallery permission
    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(storagePermission, STORAGE_REQUEST);
        }
    }

    private void requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(cameraPermission, CAMERA_REQUEST);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(KAZ_FrameListActivity.this, KAZ_HomeActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}