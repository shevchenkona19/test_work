package sheva.vkvideofeed.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sheva.vkvideofeed.mvp.datamanager.DataManager;
import sheva.vkvideofeed.mvp.model.api.ServerAPI;
import sheva.vkvideofeed.mvp.model.repositories.SharedPreferencesRepository;
import sheva.vkvideofeed.mvp.model.repositories.VkRepository;
import sheva.vkvideofeed.utils.IConstants;

/**
 * Created by shevc on 22.09.2017.
 * Let's GO!
 */
@Module
@Singleton
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public DataManager provideDataManager() {
        return new DataManager();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return application.getSharedPreferences(IConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public SharedPreferencesRepository sharedPreferencesRepository() {
        return new SharedPreferencesRepository();
    }

    @Provides
    @Singleton
    public VkRepository provideVkRepository() {
        return new VkRepository();
    }

    @Provides
    @Singleton
    public ServerAPI provideVkAPI(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IConstants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ServerAPI.class);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();
    }
}

