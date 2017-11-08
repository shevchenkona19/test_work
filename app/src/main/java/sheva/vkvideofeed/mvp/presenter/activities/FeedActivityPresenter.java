package sheva.vkvideofeed.mvp.presenter.activities;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.vkvideofeed.App;
import sheva.vkvideofeed.mvp.datamanager.DataManager;
import sheva.vkvideofeed.mvp.model.entities.Item;
import sheva.vkvideofeed.mvp.model.entities.Item_;
import sheva.vkvideofeed.mvp.model.entities.NewsFeedEntity;
import sheva.vkvideofeed.mvp.presenter.base.BasePresenter;
import sheva.vkvideofeed.mvp.presenter.interfaces.IFeedActivityPresenter;
import sheva.vkvideofeed.mvp.ui.interfaces.IFeedActivityView;

/**
 * Created by Никита on 08.11.2017.
 */

public class FeedActivityPresenter extends BasePresenter<IFeedActivityView> implements IFeedActivityPresenter {
    @Inject
    DataManager dataManager;

    private static int BASE_LOADING_COUNT = 5;

    public FeedActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void loadBase() {
        getView().onStartLoading();
        final NewsFeedEntity[] feedEntity = new NewsFeedEntity[1];
        final List<Item_> videos = new ArrayList<>();
        dataManager.getVideoFeed(BASE_LOADING_COUNT, "")
                .subscribe(new Subscriber<NewsFeedEntity>() {
                    @Override
                    public void onCompleted() {
                        String code = feedEntity[0].getResponse().getNextFrom();
                        for (Item item: feedEntity[0].getResponse().getItems()) {
                            videos.addAll(item.getVideo().getItems());
                        }
                        getView().onBaseUpdated(videos, code);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MY", "Error: " + e.getMessage());
                        getView().onFailedToLoad();
                    }

                    @Override
                    public void onNext(NewsFeedEntity newsFeedEntity) {
                        feedEntity[0] = newsFeedEntity;
                    }
                });
    }

    @Override
    public void loadPartial(String code) {
        getView().onStartLoading();
        final NewsFeedEntity[] feedEntity = new NewsFeedEntity[1];
        final List<Item_> videos = new ArrayList<>();
        dataManager.getVideoFeed(BASE_LOADING_COUNT, code)
                .subscribe(new Subscriber<NewsFeedEntity>() {
                    @Override
                    public void onCompleted() {
                        String code = feedEntity[0].getResponse().getNextFrom();
                        for (Item item: feedEntity[0].getResponse().getItems()) {
                            videos.addAll(item.getVideo().getItems());
                        }
                        getView().onPartialUpdate(videos, code);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MY", "Error: " + e.getMessage());
                        getView().onFailedToLoad();
                    }

                    @Override
                    public void onNext(NewsFeedEntity newsFeedEntity) {
                        feedEntity[0] = newsFeedEntity;
                    }
                });
    }

    @Override
    public void logout() {
        dataManager.logout();
        getView().onLogout();
    }
}
