package xyz.bringoff.yalantistask1.data.remote;

public interface ApiConstants {

    String API_BASE_URL = "http://dev-contact.yalantis.com/rest/v1/";

    class RequestStatusFilter {

        String IN_PROGRESS = "0,9,5,7,8";
        String DONE = "10,6";
        String PENDING = "1,3,4";
    }
}
