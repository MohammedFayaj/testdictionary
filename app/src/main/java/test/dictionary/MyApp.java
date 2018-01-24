package test.dictionary;

import android.app.Application;
import android.content.Context;

import test.dictionary.dagger.AppModule;
import test.dictionary.dagger.DaggerMyComponent;
import test.dictionary.dagger.MyComponent;
import test.dictionary.dagger.NetModule;

/**

 */

public class MyApp extends Application {

    MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myComponent = DaggerMyComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule()).build();

    }

    public MyComponent getMyComponent(Context context) {
        return ((MyApp) context.getApplicationContext()).myComponent;


    }
}
