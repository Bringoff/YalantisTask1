package xyz.bringoff.yalantistask1;

import xyz.bringoff.yalantistask1.data.RequestDataSourceInterface;
import xyz.bringoff.yalantistask1.data.RequestRepository;

public class Injection {

    public static RequestDataSourceInterface provideRequestDataSource() {
        return RequestRepository.getInstance();
    }
}
