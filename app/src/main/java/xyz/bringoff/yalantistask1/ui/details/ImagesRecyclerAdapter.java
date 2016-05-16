package xyz.bringoff.yalantistask1.ui.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import xyz.bringoff.yalantistask1.R;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImagesRecyclerAdapter.Holder> {

    private Context mContext;
    private List<String> mUrls;

    public ImagesRecyclerAdapter() {
        mUrls = new ArrayList<>();
    }

    public void setUrls(List<String> urls) {
        mUrls = urls;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return mUrls != null ? mUrls.size() : 0;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String url = mUrls.get(position);
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.mImageView);
    }

    protected static class Holder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public Holder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
