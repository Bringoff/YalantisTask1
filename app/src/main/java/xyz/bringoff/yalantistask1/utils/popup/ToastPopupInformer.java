package xyz.bringoff.yalantistask1.utils.popup;

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

public class ToastPopupInformer implements PopupInformer {

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
        showToast(rootView, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showLongPopup(View rootView, String message) {
        showToast(rootView, message, Toast.LENGTH_LONG);
    }

    private void showToast(View rootView, String message, int duration) {
        Toast.makeText(rootView.getContext(), message, duration).show();
    }
}
