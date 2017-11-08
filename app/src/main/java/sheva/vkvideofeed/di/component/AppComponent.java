package sheva.vkvideofeed.di.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.vkvideofeed.di.modules.AppModule;
import sheva.vkvideofeed.mvp.datamanager.DataManager;
import sheva.vkvideofeed.mvp.model.repositories.SharedPreferencesRepository;
import sheva.vkvideofeed.mvp.model.repositories.VkRepository;
import sheva.vkvideofeed.mvp.presenter.activities.FeedActivityPresenter;
import sheva.vkvideofeed.mvp.presenter.activities.LoginActivityPresenter;
import sheva.vkvideofeed.mvp.presenter.activities.VideoActivityPresenter;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(DataManager dataManager);

    void inject(SharedPreferencesRepository sharedPreferencesRepository);

    void inject(VkRepository vkRepository);

    void inject(LoginActivityPresenter loginActivityPresenter);

    void inject(FeedActivityPresenter feedActivityPresenter);

    void inject(VideoActivityPresenter videoActivityPresenter);
}
