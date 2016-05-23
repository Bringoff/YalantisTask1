package xyz.bringoff.yalantistask1.ui.tickets;

import java.util.List;

import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.ui.base.mvp.BasePresenter;
import xyz.bringoff.yalantistask1.ui.base.mvp.BaseView;

public interface TicketsMVP {

    interface View extends BaseView {

        void showTickets(List<Ticket> tickets);

        void showDetails(int ticketId);

        void showProgress();

        void hideProgress();

        void showError(Throwable errorCause);
    }

    interface Presenter extends BasePresenter {

        void bindView(View view);

        void onShowTickets();

        void onShowTicketDetails(int ticketPosition);
    }
}
