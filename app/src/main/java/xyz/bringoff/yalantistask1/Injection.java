package xyz.bringoff.yalantistask1;

import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.TicketRepository;
import xyz.bringoff.yalantistask1.data.remote.EContactApiFactory;
import xyz.bringoff.yalantistask1.data.remote.EContactApiService;

public class Injection {

    public static ITicketRepository provideRequestRepository() {
        TicketRepository repository = TicketRepository.getInstance();
        if (repository.getApiService() == null) {
            repository.setApiService(provideEContactApiService());
        }
        return repository;
    }

    public static EContactApiService provideEContactApiService() {
        return EContactApiFactory.getEContactApiService();
    }
}
