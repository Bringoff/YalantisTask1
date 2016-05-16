package xyz.bringoff.yalantistask1.utils.popup;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarPopupInformer implements PopupInformer {

    @Override
    public void showShortPopup(View rootView, @StringRes int messageResId) {
        showShortPopup(rootView, rootView.getContext().getString(messageResId));
    }

    @Override
    public void showLongPopup(View rootView, @StringRes int messageResId) {
        showLongPopup(rootView, rootView.getContext().getString(messageResId));
    }

    @Override
    public void showShortPopup(View rootView, String message) {
        showShackbar(rootView, message, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void showLongPopup(View rootView, String message) {
        showShackbar(rootView, message, Snackbar.LENGTH_LONG);
    }

    private void showShackbar(View rootView, String message, int duration) {
        Snackbar.make(rootView, message, duration).show();
    }
}
