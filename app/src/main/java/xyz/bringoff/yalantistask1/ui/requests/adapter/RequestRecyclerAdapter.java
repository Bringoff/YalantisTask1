package xyz.bringoff.yalantistask1.ui.requests.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.entity.Ticket;
import xyz.bringoff.yalantistask1.utils.DateUtils;

public class RequestRecyclerAdapter extends RecyclerView.Adapter<RequestRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Ticket> mTickets;
    private OnItemClickListener mItemClickListener;

    public RequestRecyclerAdapter(Context context, OnItemClickListener clickListener) {
        mContext = context;
        mItemClickListener = clickListener;
        mTickets = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.request_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setTicket(mTickets.get(position));
    }

    @Override
    public int getItemCount() {
        return mTickets.size();
    }

    public void setTickets(List<Ticket> tickets) {
        mTickets = tickets;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private Ticket mTicket;

        private ImageView mRequestTypeImageView;
        private TextView mRequestTypeTextView;
        private TextView mRequestAddressTextView;
        private TextView mRequestEndDateTextView;
        private TextView mRequestBeginDateDiffTextView;
        private TextView mLikesTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClicked(mTickets.indexOf(mTicket));
                }
            });
            mRequestTypeImageView = (ImageView) itemView.findViewById(R.id.request_type_icon_image_view);
            mRequestTypeTextView = (TextView) itemView.findViewById(R.id.request_type_text_view);
            mRequestAddressTextView = (TextView) itemView.findViewById(R.id.request_address_text_view);
            mRequestEndDateTextView = (TextView) itemView.findViewById(R.id.request_due_date_text_view);
            mRequestBeginDateDiffTextView = (TextView) itemView.findViewById(R.id.request_days_left_text_view);
            mLikesTextView = (TextView) itemView.findViewById(R.id.likes_text_view);
        }

        public void setTicket(Ticket ticket) {
            mTicket = ticket;
            setRequestType(mTicket.getType().getName());
            setRequestAddress(mTicket.getUser().getAddress().getCity().getName()
                    + ", " + mTicket.getUser().getAddress().getStreet().getName());
            setBeginDate(mTicket.getCreatedDate());
            setEndDate(mTicket.getDeadline());
            setLikes(mTicket.getLikesCounter());
        }

        private void setRequestType(String type) {
            mRequestTypeTextView.setText(type);
        }

        private void setRequestAddress(String address) {
            mRequestAddressTextView.setText(address);
        }

        private void setBeginDate(long date) {
            mRequestBeginDateDiffTextView.setText(DateUtils.unixToDateDiffString(mContext, date));
        }

        private void setEndDate(long date) {
            mRequestEndDateTextView.setText(DateUtils.unixToMediumDateString(mContext, date));
        }

        private void setLikes(int likes) {
            mLikesTextView.setText(String.valueOf(likes));
        }
    }
}
