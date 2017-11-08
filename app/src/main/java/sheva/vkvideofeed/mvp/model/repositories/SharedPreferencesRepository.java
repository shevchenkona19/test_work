package sheva.vkvideofeed.mvp.model.repositories;

import android.content.SharedPreferences;

import javax.inject.Inject;

import sheva.vkvideofeed.App;
import sheva.vkvideofeed.utils.IConstants;

public class SharedPreferencesRepository {
    @Inject
    SharedPreferences sharedPreferences;

    public SharedPreferencesRepository() {
        App.get().getAppComponent().inject(this);
    }
    public boolean isLogged() {
        return !sharedPreferences.getString(IConstants.TOKEN, "").equals("");
    }

    public void saveToken(String s) {
        sharedPreferences.edit().putString(IConstants.TOKEN, s).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(IConstants.TOKEN, "");
    }
}
