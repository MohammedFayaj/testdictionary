package test.dictionary.dagger;

import dagger.Component;
import test.dictionary.ui.MainActivity;

@PerApplication
@Component(modules = {AppModule.class, NetModule.class})

public interface MyComponent {
    void injectMe(MainActivity mainActivity);
}