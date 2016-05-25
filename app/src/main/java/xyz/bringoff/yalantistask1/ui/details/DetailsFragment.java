package xyz.bringoff.yalantistask1.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import xyz.bringoff.yalantistask1.Injection;
import xyz.bringoff.yalantistask1.PresenterHolder;
import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.ui.base.BaseFragment;
import xyz.bringoff.yalantistask1.utils.DateUtils;
import xyz.bringoff.yalantistask1.utils.popup.PopupInformer;
import xyz.bringoff.yalantistask1.utils.recyclerutils.HorizontalSpaceItemDecoration;
import xyz.bringoff.yalantistask1.view.CaptionValueView;

public class DetailsFragment extends BaseFragment implements DetailsMVP.View {

    private static final String KEY_TICKET_ID = "ticket_id";

    @BindView(R.id.status_text_view)
    TextView mStatusTextView;
    @BindView(R.id.created_captionvalue_view)
    CaptionValueView mCreatedView;
    @BindView(R.id.registered_captionvalue_view)
    CaptionValueView mRegisteredView;
    @BindView(R.id.solve_to_captionvalue_view)
    CaptionValueView mSolveToView;
    @BindView(R.id.responsible_captionvalue_view)
    CaptionValueView mResponsibleView;
    @BindView(R.id.detailed_description_text_view)
    TextView mDescriptionTextView;
    @BindView(R.id.type_text_view)
    TextView mTypeTextView;
    @BindView(R.id.images_recycler_view)
    RecyclerView mImagesRecyclerView;
    private ImagesRecyclerAdapter mImagesAdapter;

    private PopupInformer mPopupInformer;

    private DetailsMVP.Presenter mPresenter;
    private PresenterHolder mPresenterHolder;

    public static Fragment newInstance(int ticketId) {
        Bundle args = new Bundle();
        args.putInt(KEY_TICKET_ID, ticketId);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPopupInformer = Injection.provideSnackbarInformer();

        mPresenterHolder = Injection.providePresenterHolder();

        if (savedInstanceState != null) {
            mPresenter = mPresenterHolder.getPresenter(mPresenter.getClass());
        }
        if (mPresenter == null) {
            mPresenter = new DetailsPresenter(Injection.provideTicketRepository(getContext()),
                    getArguments().getInt(KEY_TICKET_ID));
            mPresenter.bindView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        initImagesList();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttach();
        mPresenter.onShowTicket();
    }

    private void initImagesList() {
        mImagesRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(
                (int) getResources().getDimension(R.dimen.default_vertical_margin)));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        mImagesRecyclerView.setLayoutManager(lm);
        mImagesAdapter = new ImagesRecyclerAdapter();
        mImagesRecyclerView.setAdapter(mImagesAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mPresenterHolder.putPresenter(mPresenter.getClass(), mPresenter);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }

    @Override
    public void showTicket(Ticket ticket) {
        mTypeTextView.setText(ticket.getType());
        mStatusTextView.setText(ticket.getStatusName());
        mCreatedView.setValue(DateUtils.unixToMediumDateString(getActivity(), ticket.getCreatingDate()));
        mRegisteredView.setValue(DateUtils.unixToMediumDateString(getActivity(), ticket.getRegisteringDate()));
        mSolveToView.setValue(DateUtils.unixToMediumDateString(getActivity(), ticket.getDeadlineDate()));
        mDescriptionTextView.setText(ticket.getDescription());
    }

    @Override
    public void showError(Throwable errorCause) {
        mPopupInformer.showLongPopup(getView(), errorCause.getLocalizedMessage());
    }
}
