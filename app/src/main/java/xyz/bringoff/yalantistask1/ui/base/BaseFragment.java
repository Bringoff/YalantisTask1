package xyz.bringoff.yalantistask1.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @LayoutRes
    public abstract int getLayoutId();

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
        super.onDestroy();
    }
}
