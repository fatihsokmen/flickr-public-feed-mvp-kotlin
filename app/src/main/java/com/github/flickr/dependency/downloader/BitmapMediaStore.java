package com.github.flickr.dependency.downloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.UUID;

import javax.inject.Inject;

import rx.Completable;
import rx.functions.Action0;

public class BitmapMediaStore implements MediaStore {

    private final @NonNull
    Context context;

    @Inject
    BitmapMediaStore(@NonNull Context context) {
        this.context = context;
    }


    @Override
    public Completable saveBitmap(@NonNull final Bitmap bitmap) {
        return Completable.fromAction(new Action0() {
            @Override
            public void call() {
                String url = android.provider.MediaStore.Images.Media.insertImage(
                        context.getContentResolver(),
                        bitmap,
                        UUID.randomUUID().toString(),
                        null);

                if (url == null) {
                    throw new SecurityException("Can't save to gallery");
                }
            }
        });
    }

}
