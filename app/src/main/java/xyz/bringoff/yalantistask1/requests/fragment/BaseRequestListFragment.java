package xyz.bringoff.yalantistask1.requests.fragment;

import android.support.v4.app.Fragment;

import java.util.List;

import xyz.bringoff.yalantistask1.data.Request;

public abstract class BaseRequestListFragment extends Fragment {

    protected List<Request> requests;

    public void setRequests(List<Request> requests) {
        this.requests = requests;
        notifyAdapterAboutRequests();
    }

    protected abstract void notifyAdapterAboutRequests();
}
