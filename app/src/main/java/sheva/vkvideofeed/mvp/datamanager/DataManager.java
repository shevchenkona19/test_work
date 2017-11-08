package sheva.vkvideofeed.mvp.datamanager;

import com.vk.sdk.VKSdk;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import sheva.vkvideofeed.App;
import sheva.vkvideofeed.mvp.model.entities.Item;
import sheva.vkvideofeed.mvp.model.entities.Item_;
import sheva.vkvideofeed.mvp.model.entities.NewsFeedEntity;
import sheva.vkvideofeed.mvp.model.entities.videoentity.VideoEntity;
import sheva.vkvideofeed.mvp.model.repositories.SharedPreferencesRepository;
import sheva.vkvideofeed.mvp.model.repositories.VkRepository;

/**
 * Created by Никита on 08.11.2017.
 */

public class DataManager {
    @Inject
    SharedPreferencesRepository sharedPreferencesRepository;
    @Inject
    VkRepository vkRepository;

    public DataManager() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<NewsFeedEntity> getVideoFeed(int count, String start_from) {
        return vkRepository.getVideoFeed(count, start_from, getToken());
    }

    public Observable<VideoEntity> getVideo(String id) {
        return vkRepository.getPlayerForVideoIdRequest(id, getToken());
    }

    public void saveToken(String token) {
        sharedPreferencesRepository.saveToken(token);
    }

    public boolean isLogged() {
        return sharedPreferencesRepository.isLogged();
    }

    private String getToken() {
        return sharedPreferencesRepository.getToken();
    }

    public void logout() {
        VKSdk.logout();
        sharedPreferencesRepository.saveToken("");
    }
}
