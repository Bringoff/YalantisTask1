package xyz.bringoff.yalantistask1.requests.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.Request;
import xyz.bringoff.yalantistask1.utils.DateUtils;

public class RequestListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Request> mRequests;

    public RequestListAdapter(Context context) {
        mContext = context;
    }

    public void setRequests(List<Request> requests) {
        mRequests = requests;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mRequests.size();
    }

    @Override
    public Request getItem(int position) {
        return mRequests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        ViewHolder holder;
        if (convertView == null) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.request_list_item, parent, false);
            holder = new ViewHolder(itemView);
            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }
        holder.setRequest(mRequests.get(position));
        return itemView;
    }

    private class ViewHolder {

        private Request mRequest;

        private ImageView mRequestTypeImageView;
        private TextView mRequestTypeTextView;
        private TextView mRequestAddressTextView;
        private TextView mRequestEndDateTextView;
        private TextView mRequestBeginDateDiffTextView;
        private TextView mLikesTextView;

        public ViewHolder(View itemView) {
            mRequestTypeImageView = (ImageView) itemView.findViewById(R.id.request_type_icon_image_view);
            mRequestTypeTextView = (TextView) itemView.findViewById(R.id.request_type_text_view);
            mRequestAddressTextView = (TextView) itemView.findViewById(R.id.request_address_text_view);
            mRequestEndDateTextView = (TextView) itemView.findViewById(R.id.request_due_date_text_view);
            mRequestBeginDateDiffTextView = (TextView) itemView.findViewById(R.id.request_days_left_text_view);
            mLikesTextView = (TextView) itemView.findViewById(R.id.likes_text_view);
        }

        public void setRequest(Request request) {
            mRequest = request;
            setRequestType(mRequest.getRequestType());
            setRequestAddress(mRequest.getAddress());
            setBeginDate(mRequest.getCreatedDate());
            setEndDate(mRequest.getSolveToDate());
            setLikes(mRequest.getLikes());
        }

        private void setRequestType(Request.RequestType type) {
            // As I don't have icons, I set a default one for now
            mRequestTypeImageView.setImageResource(R.drawable.ic_doc);

            switch (type) {
                case BUILDING:
                    mRequestTypeTextView.setText(mContext.getString(R.string.landscaping_building));
                    break;
                case UTILITY_SECTOR:
                    mRequestTypeTextView.setText(mContext.getString(R.string.utility_sector));
                    break;
                case OTHER:
                    mRequestTypeTextView.setText(mContext.getString(R.string.other));
                    break;

                default:
                    break;
            }
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
