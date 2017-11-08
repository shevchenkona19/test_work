package sheva.vkvideofeed.mvp.presenter.activities;

import android.util.Log;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.vkvideofeed.App;
import sheva.vkvideofeed.mvp.datamanager.DataManager;
import sheva.vkvideofeed.mvp.model.entities.videoentity.VideoEntity;
import sheva.vkvideofeed.mvp.presenter.base.BasePresenter;
import sheva.vkvideofeed.mvp.presenter.interfaces.IVideoActivityPresenter;
import sheva.vkvideofeed.mvp.ui.interfaces.IVideoActivityView;

/**
 * Created by Никита on 08.11.2017.
 */

public class VideoActivityPresenter extends BasePresenter<IVideoActivityView> implements IVideoActivityPresenter {
    @Inject
    DataManager dataManager;

    public VideoActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void getVideoPlayerUrl(String videoId) {
        final VideoEntity[] video = new VideoEntity[1];
        dataManager.getVideo(videoId)
                .subscribe(new Subscriber<VideoEntity>() {
                    @Override
                    public void onCompleted() {
                        getView().updateWebView(video[0].getResponse().getItems().get(0).getPlayer());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MY", e.getMessage());
                        getView().onErrorInLoadingPlayer();
                    }

                    @Override
                    public void onNext(VideoEntity videoEntity) {
                        video[0] = videoEntity;
                    }
                });
    }
}
