package com.github.flickr.dependency.downloader;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class BitmapHolderImpl implements BitmapHolder {

    private final @NonNull
    Bitmap bitmap;

    BitmapHolderImpl(@NonNull Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public @NonNull
    Bitmap getBitmap() {
        return bitmap;
    }
}
