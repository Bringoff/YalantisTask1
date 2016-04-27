package xyz.bringoff.yalantistask1.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RequestRepository implements RequestDataSourceInterface {

    private static final int DUMMY_DATA_COUNT = 10;
    private Map<String, Request> mRequests;

    private RequestRepository() {
    }

    public static RequestRepository getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /*
    * Don't think much about what happens here, it is just dummy data generating
    */
    private Map<String, Request> getDummyRequests() {
        Map<String, Request> requests = new HashMap<>(DUMMY_DATA_COUNT);
        Random random = new Random();
        Request request;
        for (int i = 0; i < DUMMY_DATA_COUNT; i++) {
            int typeIndex = random.nextInt(Request.RequestType.values().length);
            Request.RequestType type = Request.RequestType.values()[typeIndex];
            String address = "Some address";
            String status = "Some status";
            long created = System.currentTimeMillis() - random.nextInt(10) * 86400000;
            long registered = created + random.nextInt(10) * 86400000;
            long solveTo = registered + random.nextInt(10) * 86400000;
            int likes = random.nextInt(25);
            String responsible = "Who's responsible?";
            String description = "Describe it";

            request = new Request(type, address, status, created, registered, solveTo,
                    responsible, description, likes);
            requests.put(request.getId(), request);
        }
        return requests;
    }

    @Override
    public void getRequests(@NonNull LoadRequestsCallback callback) {
        if (mRequests == null) {
            mRequests = getDummyRequests();
        }
        callback.onRequestsLoaded(new ArrayList<>(mRequests.values()));
    }

    @Override
    public void getRequest(@NonNull String requestId, GetRequestCallback callback) {
        if (mRequests == null) {
            callback.onDataNotAvailable();
            return;
        }
        if (mRequests.containsKey(requestId)) {
            callback.onRequestLoaded(mRequests.get(requestId));
        } else {
            callback.onDataNotAvailable();
        }
    }

    private static class InstanceHolder {
        private static final RequestRepository INSTANCE = new RequestRepository();
    }

}
