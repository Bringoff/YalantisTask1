package xyz.bringoff.yalantistask1.requests.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

import xyz.bringoff.yalantistask1.data.Request;

public abstract class BaseRequestListFragment extends Fragment {

    protected List<Request> requests;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
        notifyAdapterAboutRequests();
    }

    protected abstract void notifyAdapterAboutRequests();
}
