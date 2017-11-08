package sheva.vkvideofeed;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.vk.sdk.VKSdk;

import sheva.vkvideofeed.di.component.AppComponent;
import sheva.vkvideofeed.di.component.DaggerAppComponent;
import sheva.vkvideofeed.di.modules.AppModule;

/**
 * Created by Никита on 08.11.2017.
 */

public class App extends Application {

    private static App instance;
    private AppComponent appComponent;
    public AppComponent getAppComponent() {
        return appComponent;
    }
    public static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        Fresco.initialize(this);
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
