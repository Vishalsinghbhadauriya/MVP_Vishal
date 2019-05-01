package com.example.mvp_vishal.networking;

import android.content.Context;

import com.example.mvp_vishal.BuildConfig;
import com.example.mvp_vishal.scopes.PerApplication;
import com.example.mvp_vishal.session.SessionManager;

import java.io.File;
import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @PerApplication
    public File providesCacheFile(Context context) {
        return new File(context.getCacheDir(), "responses");
    }

    @Provides
    @PerApplication
    Retrofit provideCall(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @PerApplication
    @SuppressWarnings("unused")
    public NetworkRequest providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(NetworkRequest.class);
    }

    @Provides
    @PerApplication
    @SuppressWarnings("unused")
    public NetworkService providesService(
            NetworkRequest networkRequest) {
        return new NetworkService(networkRequest);
    }

    @Provides
    @PerApplication
    public OkHttpClient providesOkHttpClient(Interceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @PerApplication
    public Cache providesCache(File cacheFile) {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    @Provides
    @PerApplication
    public Interceptor providesInterceptor(final SessionManager sessionManager) {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .addHeader("apiKey", sessionManager.getToken())
                        .header("Content-Type", "application/json")
                        .removeHeader("Pragma")
                        .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                        .build();

                okhttp3.Response response = chain.proceed(request);
                response.cacheResponse();
                // Customize or return the response
                return response;
            }
        };
    }

}
