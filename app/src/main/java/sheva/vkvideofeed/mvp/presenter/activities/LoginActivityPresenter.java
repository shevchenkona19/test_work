package sheva.vkvideofeed.mvp.presenter.activities;

import javax.inject.Inject;

import sheva.vkvideofeed.App;
import sheva.vkvideofeed.mvp.datamanager.DataManager;
import sheva.vkvideofeed.mvp.presenter.base.BasePresenter;
import sheva.vkvideofeed.mvp.presenter.interfaces.ILoginActivityPresenter;
import sheva.vkvideofeed.mvp.ui.interfaces.ILoginActivityView;

/**
 * Created by Никита on 08.11.2017.
 */

public class LoginActivityPresenter extends BasePresenter<ILoginActivityView> implements ILoginActivityPresenter {
    @Inject
    DataManager dataManager;

    public LoginActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void checkLogged() {
        if (dataManager.isLogged()) {
            getView().onLoggedSuccessfully();
        }
    }

    @Override
    public void onVkAuthorised(String token) {
        dataManager.saveToken(token);
        getView().onLoggedSuccessfully();
    }
}
