package sheva.vkvideofeed.mvp.presenter.base;

import sheva.vkvideofeed.mvp.ui.interfaces.IView;

/**
 * Created by Никита on 08.11.2017.
 */

public abstract class BasePresenter<V extends IView> {
    private V v;

    public V getView() {
        return v;
    }

    public void bind(V v) {
        this.v = v;
    }

    public void unbind() {
        v = null;
    }
}
