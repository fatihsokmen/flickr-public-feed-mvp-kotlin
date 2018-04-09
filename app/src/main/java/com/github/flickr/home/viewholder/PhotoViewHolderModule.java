package com.github.flickr.home.viewholder;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.flickr.dependency.scope.ViewHolderScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = {
        PhotoViewHolderModule.Bindings.class,
        PhotoViewHolderModule.PhotoViewHolderLayoutModule.class
})
public class PhotoViewHolderModule {

    @Module
    public interface Bindings {

        @Binds
        @ViewHolderScope
        PhotoViewHolder provideViewHolder(@NonNull PhotoViewHolderView impl);

        @Binds
        @ViewHolderScope
        PhotoViewHolderContract.Presenter provideViewHolderPresenter(
                @NonNull PhotoViewHolderPresenter impl);
    }

    @Module
    public static class PhotoViewHolderLayoutModule {

        private final @LayoutRes int layoutId;

        public PhotoViewHolderLayoutModule(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
        }

        @Provides
        @ViewHolderScope
        View provideViewHolderLayout(@NonNull ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        }
    }
}