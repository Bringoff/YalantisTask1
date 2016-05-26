package xyz.bringoff.yalantistask1.ui.tickets.adapter;

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
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.utils.DateUtils;

public class TicketRecyclerAdapter extends RecyclerView.Adapter<TicketRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Ticket> mTickets;
    private OnItemClickListener mItemClickListener;

    public TicketRecyclerAdapter(Context context, OnItemClickListener clickListener) {
        mContext = context;
        mItemClickListener = clickListener;
        mTickets = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.ticket_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTicket(mTickets.get(position));
    }

    @Override
    public int getItemCount() {
        return mTickets.size();
    }

    public void setTickets(List<Ticket> tickets) {
        mTickets.clear();
        mTickets.addAll(tickets);
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private Ticket mTicket;

        private ImageView mTicketTypeImageView;
        private TextView mTicketTypeTextView;
        private TextView mTicketAddressTextView;
        private TextView mTicketEndDateTextView;
        private TextView mTicketBeginDateDiffTextView;
        private TextView mLikesTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClicked(mTickets.indexOf(mTicket));
                }
            });
            mTicketTypeImageView = (ImageView) itemView.findViewById(R.id.ticket_type_icon_image_view);
            mTicketTypeTextView = (TextView) itemView.findViewById(R.id.ticket_type_text_view);
            mTicketAddressTextView = (TextView) itemView.findViewById(R.id.ticket_address_text_view);
            mTicketEndDateTextView = (TextView) itemView.findViewById(R.id.ticket_due_date_text_view);
            mTicketBeginDateDiffTextView = (TextView) itemView.findViewById(R.id.ticket_days_left_text_view);
            mLikesTextView = (TextView) itemView.findViewById(R.id.likes_text_view);
        }

        public void bindTicket(Ticket ticket) {
            mTicket = ticket;
            setTicketType(mTicket.getType());
            setTicketAddress(mTicket.getAddress());
            setBeginDate(mTicket.getCreatingDate());
            setEndDate(mTicket.getDeadlineDate());
            setLikes(mTicket.getLikesCount());
        }

        private void setTicketType(String type) {
            mTicketTypeTextView.setText(type);
        }

        private void setTicketAddress(String address) {
            mTicketAddressTextView.setText(address);
        }

        private void setBeginDate(long date) {
            mTicketBeginDateDiffTextView.setText(DateUtils.unixToDateDiffString(mContext, date));
        }

        private void setEndDate(long date) {
            mTicketEndDateTextView.setText(DateUtils.unixToMediumDateString(mContext, date));
        }

        private void setLikes(int likes) {
            mLikesTextView.setText(String.valueOf(likes));
        }
    }
}
