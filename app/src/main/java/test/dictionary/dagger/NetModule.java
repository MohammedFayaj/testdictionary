package test.dictionary.dagger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.dictionary.Utils.Constants;
import test.dictionary.network.DictionaryApi;
import test.dictionary.network.MashableInterceptor;

@Module
public class NetModule {

    @Provides
    @PerApplication
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @PerApplication
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @PerApplication
    static DictionaryApi provideItems(Gson gson, OkHttpClient okHttpClient) {
        OkHttpClient.Builder httpClientBuilder = okHttpClient.newBuilder();
        httpClientBuilder.addInterceptor(new MashableInterceptor());


        return new Retrofit.Builder()
                .baseUrl(Constants.MASHABLE_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(DictionaryApi.class);
    }
}
