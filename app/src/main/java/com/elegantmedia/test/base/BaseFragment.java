package com.elegantmedia.test.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class BaseFragment extends Fragment {

    private BaseActivity activity;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public BaseActivity getBaseActivity() {
        return activity;
    }

    public void showMessage(String message) {
        activity.showMessage(message);
    }

    public void showWaiting() {
        activity.showWaiting();
    }

    public void dismissWaiting() {
        activity.dismissWaiting();
    }
}
