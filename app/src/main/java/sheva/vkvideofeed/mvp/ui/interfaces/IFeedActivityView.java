package sheva.vkvideofeed.mvp.ui.interfaces;

import java.util.List;

import sheva.vkvideofeed.mvp.model.entities.Item_;

/**
 * Created by Никита on 08.11.2017.
 */

public interface IFeedActivityView extends IActivityView {
    void onBaseUpdated(List<Item_> items, String nextPageCode);
    void onPartialUpdate(List<Item_> items, String nextPageCode);
    void onStartLoading();
    void onFailedToLoad();

    void onLogout();

}
