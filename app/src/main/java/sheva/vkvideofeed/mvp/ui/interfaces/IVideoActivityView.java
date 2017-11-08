package sheva.vkvideofeed.mvp.ui.interfaces;

/**
 * Created by Никита on 08.11.2017.
 */

public interface IVideoActivityView extends IActivityView {
    void updateWebView(String url);

    void onErrorInLoadingPlayer();

}
