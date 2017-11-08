package sheva.vkvideofeed.mvp.model.repositories;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sheva.vkvideofeed.App;
import sheva.vkvideofeed.mvp.model.api.ServerAPI;
import sheva.vkvideofeed.mvp.model.entities.NewsFeedEntity;
import sheva.vkvideofeed.mvp.model.entities.videoentity.VideoEntity;

/**
 * Created by Никита on 08.11.2017.
 */

public class VkRepository {
    private static final String VK_API_VERSION = "5.69";
    @Inject
    ServerAPI api;

    public VkRepository() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<NewsFeedEntity> getVideoFeed(int count, String startFrom, String token) {
        return api.getVideosFromFeed("video", count, startFrom, token, VK_API_VERSION)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<VideoEntity> getPlayerForVideoIdRequest(String videoId, String token) {
        return api.getVideoById(videoId, 1, token, VK_API_VERSION)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
