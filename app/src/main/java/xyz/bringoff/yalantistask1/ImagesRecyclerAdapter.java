package xyz.bringoff.yalantistask1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImagesRecyclerAdapter.Holder> {

    private Context mContext;
    private List<String> mUrls;

    public ImagesRecyclerAdapter(Context context, List<String> urls) {
        mContext = context;
        mUrls = urls;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
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
