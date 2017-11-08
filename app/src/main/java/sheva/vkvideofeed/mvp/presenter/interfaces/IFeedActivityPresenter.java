package sheva.vkvideofeed.mvp.presenter.interfaces;

/**
 * Created by Никита on 08.11.2017.
 */

public interface IFeedActivityPresenter {
    void loadBase();
    void loadPartial(String code);
    void logout();
}
