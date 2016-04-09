package xyz.bringoff.yalantistask1.utils.recyclerutils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int mSpaceSize;

    public HorizontalSpaceItemDecoration(int spaceSize) {
        mSpaceSize = spaceSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = mSpaceSize;
    }
}
