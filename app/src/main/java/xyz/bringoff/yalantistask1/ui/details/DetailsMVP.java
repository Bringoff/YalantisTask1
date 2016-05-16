package xyz.bringoff.yalantistask1.ui.details;

import xyz.bringoff.yalantistask1.data.entity.Ticket;
import xyz.bringoff.yalantistask1.ui.base.mvp.BasePresenter;
import xyz.bringoff.yalantistask1.ui.base.mvp.BaseView;

public interface DetailsMVP {

    interface View extends BaseView {

        void showTicket(Ticket ticket);

        void showError(Throwable errorCause);
    }

    interface Presenter extends BasePresenter {

        void bindView(View view);

        void onTickedShowingRequested();
    }
}
