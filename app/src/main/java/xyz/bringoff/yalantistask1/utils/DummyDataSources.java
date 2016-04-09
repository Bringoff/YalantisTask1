package xyz.bringoff.yalantistask1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import xyz.bringoff.yalantistask1.data.Request;

public class DummyDataSources {

    private static final int DUMMY_DATA_COUNT = 10;

    /*
    * Don't think much about what happens here, it is just dummy data generating
     */
    public static List<Request> getDummyRequests() {
        List<Request> requests = new ArrayList<>(DUMMY_DATA_COUNT);
        Random random = new Random();
        Request request;
        for (int i = 0; i < DUMMY_DATA_COUNT; i++) {
            int typeIndex = random.nextInt(Request.RequestType.values().length);
            Request.RequestType type = Request.RequestType.values()[typeIndex];
            String address = UUID.randomUUID().toString();
            long created = System.currentTimeMillis() - random.nextInt(1000000);
            long solveTo = created + random.nextInt(1000000);
            int likes = random.nextInt(25);

            request = new Request(type, address, created, solveTo, likes);
            requests.add(request);
        }
        return requests;
    }
}
