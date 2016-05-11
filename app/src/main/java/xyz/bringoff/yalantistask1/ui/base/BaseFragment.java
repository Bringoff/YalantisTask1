package xyz.bringoff.yalantistask1.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

    private CompositeSubscription mCompositeSubscription;

    protected CompositeSubscription getCompositeSubscription() {
        return mCompositeSubscription;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
        super.onDestroy();
    }
}
