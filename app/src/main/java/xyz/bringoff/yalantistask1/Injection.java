package xyz.bringoff.yalantistask1;

import android.content.Context;

import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.TicketRepository;
import xyz.bringoff.yalantistask1.data.local.TicketLocalRepository;
import xyz.bringoff.yalantistask1.data.local.db.DbOpenHelper;
import xyz.bringoff.yalantistask1.data.local.db.ITicketStorage;
import xyz.bringoff.yalantistask1.data.local.db.TicketStorage;
import xyz.bringoff.yalantistask1.data.remote.EContactApiFactory;
import xyz.bringoff.yalantistask1.data.remote.EContactApiService;
import xyz.bringoff.yalantistask1.utils.popup.PopupInformer;
import xyz.bringoff.yalantistask1.utils.popup.SnackbarPopupInformer;
import xyz.bringoff.yalantistask1.utils.popup.ToastPopupInformer;

public class Injection {

    public static PresenterHolder providePresenterHolder() {
        return PresenterHolder.getInstance();
    }

    public static ITicketRepository provideTicketRepository(Context context) {
        return TicketRepository.getInstance(
                provideEContactApiService(),
                provideLocalTicketRepository(provideDbHelper(context)),
                provideTicketStorage(provideDbHelper(context)));
    }

    public static EContactApiService provideEContactApiService() {
        return EContactApiFactory.getEContactApiService();
    }

    public static DbOpenHelper provideDbHelper(Context context) {
        return new DbOpenHelper(context);
    }

    public static ITicketRepository provideLocalTicketRepository(DbOpenHelper openHelper) {
        return new TicketLocalRepository(openHelper);
    }

    public static ITicketStorage provideTicketStorage(DbOpenHelper openHelper) {
        return new TicketStorage(openHelper);
    }

    public static PopupInformer provideToastInformer() {
        return new ToastPopupInformer();
    }

    public static PopupInformer provideSnackbarInformer() {
        return new SnackbarPopupInformer();
    }

}
