package xyz.bringoff.yalantistask1.data;

import android.support.annotation.NonNull;

import java.util.List;

public interface RequestDataSource {

    void getRequests(@NonNull LoadRequestsCallback callback);

    void getRequest(@NonNull String requestId, GetRequestCallback callback);

    interface LoadRequestsCallback {

        void onRequestsLoaded(List<Request> requests);

        void onDataNotAvailable();
    }

    interface GetRequestCallback {

        void onRequestLoaded(Request request);

        void onDataNotAvailable();
    }
}
