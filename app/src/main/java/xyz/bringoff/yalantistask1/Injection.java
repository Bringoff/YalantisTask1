package xyz.bringoff.yalantistask1;

import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.TicketRepository;
import xyz.bringoff.yalantistask1.data.remote.EContactApiFactory;
import xyz.bringoff.yalantistask1.data.remote.EContactApiService;
import xyz.bringoff.yalantistask1.utils.popup.PopupInformer;
import xyz.bringoff.yalantistask1.utils.popup.SnackbarPopupInformer;
import xyz.bringoff.yalantistask1.utils.popup.ToastPopupInformer;

public class Injection {

    public static PresenterHolder providePresenterHolder() {
        return PresenterHolder.getInstance();
    }

    public static ITicketRepository provideTicketRepository() {
        TicketRepository repository = TicketRepository.getInstance();
        if (repository.getApiService() == null) {
            repository.setApiService(provideEContactApiService());
        }
        return repository;
    }

    public static EContactApiService provideEContactApiService() {
        return EContactApiFactory.getEContactApiService();
    }

    public static PopupInformer provideToastInformer() {
        return new ToastPopupInformer();
    }

    public static PopupInformer provideSnackbarInformer() {
        return new SnackbarPopupInformer();
    }

}
