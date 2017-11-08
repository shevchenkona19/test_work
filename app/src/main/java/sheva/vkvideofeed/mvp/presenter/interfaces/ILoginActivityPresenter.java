package sheva.vkvideofeed.mvp.presenter.interfaces;

/**
 * Created by Никита on 08.11.2017.
 */

public interface ILoginActivityPresenter {
    void checkLogged();
    void onVkAuthorised(String token);
}
