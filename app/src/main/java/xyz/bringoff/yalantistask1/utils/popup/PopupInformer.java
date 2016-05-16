package xyz.bringoff.yalantistask1.utils.popup;

import android.support.annotation.StringRes;
import android.view.View;

public interface PopupInformer {

    void showShortPopup(View rootView, @StringRes int messageResId);

    void showLongPopup(View rootView, @StringRes int messageResId);

    void showShortPopup(View rootView, String message);

    void showLongPopup(View rootView, String message);
}
