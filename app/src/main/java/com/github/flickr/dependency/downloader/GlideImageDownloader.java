package com.github.flickr.dependency.downloader;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.AsyncSubject;

public class GlideImageDownloader implements ImageDownloader {

    @NonNull
    RequestManager glide;

    @Inject
    GlideImageDownloader(@NonNull RequestManager glide) {
        this.glide = glide;
    }

    @Override
    public @NonNull
    Observable<BitmapHolder> downloadBitmap(@NonNull String url) {
        final AsyncSubject<BitmapHolder> notifier = AsyncSubject.create();

        glide.asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource,
                                        @Nullable Transition<? super Bitmap> transition) {
                notifier.onNext(new BitmapHolderImpl(resource));
                notifier.onCompleted();
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                notifier.onError(new Throwable("Can't load image"));
                notifier.onCompleted();
            }
        });

        return notifier;
    }
}
