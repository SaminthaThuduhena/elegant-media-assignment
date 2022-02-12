package com.elegantmedia.test.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.elegantmedia.test.R;

import java.util.HashMap;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private HashMap<Integer, Fragment> mFragmentMap = new HashMap<>();
    private ProgressDialog mProgressDialog;

    /**
     * Start activity from class name
     *
     * @param activityClass class name
     */
    public void activityToActivity(Class activityClass) {
        Intent intent = new Intent(getApplication(), activityClass);
        startActivity(intent);
    }

    public void showWaiting() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, R.style.cl_progress_bar);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissWaiting() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * @param containerId       View id
     * @param fragment          Fragment that need to commit
     * @param isBackStackEnable Back stack enable or not
     */
    protected void startFragment(int containerId, Fragment fragment, boolean isBackStackEnable) {
        Log.d(TAG, "startFragment: " + fragment.getId());

        mFragmentMap.put(mFragmentMap.size(), fragment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(isBackStackEnable) transaction.addToBackStack(null);

        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    /**
     * Show general massages in toast text
     *
     * @param message Toast text message
     */
    public void showMessage(String message) {
        if (message == null || message.isEmpty()) return;

        Toast.makeText(
                this,
                message, Toast.LENGTH_SHORT
        ).show();
    }
}
