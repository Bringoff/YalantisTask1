package xyz.bringoff.yalantistask1;

import java.util.HashMap;
import java.util.Map;

import xyz.bringoff.yalantistask1.ui.base.mvp.BasePresenter;

public class PresenterHolder {

    private static volatile PresenterHolder sSingleton = null;

    private Map<Class, BasePresenter> mPresenters;

    private PresenterHolder() {
        mPresenters = new HashMap<>();
    }

    public static PresenterHolder getInstance() {
        if (sSingleton == null) {
            synchronized (PresenterHolder.class) {
                if (sSingleton == null) {
                    sSingleton = new PresenterHolder();
                }
            }
        }
        return sSingleton;
    }

    public void putPresenter(Class c, BasePresenter presenter) {
        mPresenters.put(c, presenter);
    }

    public <T extends BasePresenter> T getPresenter(Class<T> c) {
        T presenter = (T) mPresenters.get(c);
        mPresenters.remove(c);
        return presenter;
    }
}
