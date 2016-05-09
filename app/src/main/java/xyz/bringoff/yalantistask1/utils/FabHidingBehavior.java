package xyz.bringoff.yalantistask1.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

@SuppressWarnings("unused")
public class FabHidingBehavior extends FloatingActionButton.Behavior {

    private static boolean sReactOnScroll;

    static {
        sReactOnScroll = true;
    }

    public FabHidingBehavior(Context context, AttributeSet attrs) {
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return sReactOnScroll
                && (nestedScrollAxes == 2 || super.onStartNestedScroll(
                coordinatorLayout, child, directTargetChild, target, nestedScrollAxes));
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }
}