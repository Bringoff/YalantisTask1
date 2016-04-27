package xyz.bringoff.yalantistask1.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RequestRepository implements RequestDataSource {

    private static final int DUMMY_DATA_COUNT = 10;
    private List<Request> mRequests;

    private RequestRepository() {
    }

    public static RequestRepository getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /*
    * Don't think much about what happens here, it is just dummy data generating
    */
    private static List<Request> getDummyRequests() {
        List<Request> requests = new ArrayList<>(DUMMY_DATA_COUNT);
        Random random = new Random();
        Request request;
        for (int i = 0; i < DUMMY_DATA_COUNT; i++) {
            int typeIndex = random.nextInt(Request.RequestType.values().length);
            Request.RequestType type = Request.RequestType.values()[typeIndex];
            String address = UUID.randomUUID().toString();
            String status = UUID.randomUUID().toString();
            long created = System.currentTimeMillis() - random.nextInt(10) * 86400000;
            long registered = created + random.nextInt(10) * 86400000;
            long solveTo = registered + random.nextInt(10) * 86400000;
            int likes = random.nextInt(25);
            String responsible = UUID.randomUUID().toString();
            String description = UUID.randomUUID().toString();

            request = new Request(type, address, status, created, registered, solveTo,
                    responsible, description, likes);
            requests.add(request);
        }
        return requests;
    }

    @Override
    public void getRequests(@NonNull LoadRequestsCallback callback) {
        if (mRequests == null) {
            mRequests = getDummyRequests();
        }
        callback.onRequestsLoaded(mRequests);
    }

    @Override
    public void getRequest(@NonNull String requestId, GetRequestCallback callback) {
        if (mRequests == null) {
            callback.onDataNotAvailable();
            return;
        }
        for (Request request : mRequests) {
            if (request.getId().equals(requestId)) {
                callback.onRequestLoaded(request);
            }
        }
        callback.onDataNotAvailable();
    }

    private static class InstanceHolder {
        private static final RequestRepository INSTANCE = new RequestRepository();
    }

}
