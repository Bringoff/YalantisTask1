package xyz.bringoff.yalantistask1;

import xyz.bringoff.yalantistask1.data.RequestDataSource;
import xyz.bringoff.yalantistask1.data.RequestRepository;

public class Injection {

    public static RequestDataSource provideRequestDataSource() {
        return RequestRepository.getInstance();
    }
}
