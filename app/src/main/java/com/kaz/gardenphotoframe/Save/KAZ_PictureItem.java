package com.kaz.gardenphotoframe.Save;

import android.net.Uri;

public class KAZ_PictureItem {


    private Uri uri;

    public KAZ_PictureItem(Uri uri) {
        this.uri = uri;
    }

    public KAZ_PictureItem() {

    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
