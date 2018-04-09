package com.github.flickr.dependency;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module
public class NetModule {

    private final static String baseUrl = "https://api.flickr.com/services/";

    @Provides
    @Singleton
    Cache provideHttpCache(@NonNull Application app) {
        return new Cache(app.getCacheDir(), 10 * 1024 * 1024);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@NonNull Cache cache) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder okHttp = new OkHttpClient.Builder();
        okHttp.addInterceptor(loggingInterceptor);
        okHttp.cache(cache);
        return okHttp.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}