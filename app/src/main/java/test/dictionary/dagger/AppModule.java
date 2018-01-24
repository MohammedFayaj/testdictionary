package test.dictionary.dagger;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.dictionary.MyApp;

@Module
public class AppModule {

    private final MyApp application;

    public AppModule(MyApp application) {
        this.application = application;
    }


    @Provides
    @Singleton
    SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    Context provideContext() {
        return application;
    }
}
