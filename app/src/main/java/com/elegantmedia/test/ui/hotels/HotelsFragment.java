package com.elegantmedia.test.ui.hotels;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.elegantmedia.test.R;
import com.elegantmedia.test.base.BaseFragment;
import com.elegantmedia.test.databinding.HotelsFragmentBinding;
import com.elegantmedia.test.model.AppResource;
import com.elegantmedia.test.model.Datum;
import com.elegantmedia.test.ui.adapter.HotelsAdaptor;
import com.elegantmedia.test.ui.auth.AuthenticationActivity;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HotelsFragment extends BaseFragment implements HotelsAdaptor.CustomerAdaptorCallback {

    private HotelsViewModel mViewModel;
    private HotelsFragmentBinding mBinding;
    private FragmentEventListener mListener;

    private List<Datum> datams;
    private HotelsAdaptor<Datum> mAdapter;

    public static HotelsFragment newInstance() {
        return new HotelsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = HotelsFragmentBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        mListener = (FragmentEventListener) context;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HotelsViewModel.class);

        init();
        observeViewModels();
        getData();
        setHasOptionsMenu(true);
        getBaseActivity().setTitle(getString(R.string.list_view));
    }

    @Override
    public void onView(int viewType, int position) {
        Datum datum = mAdapter.getData().get(position);

        if (mListener != null)
            mListener.onMenuDetailFragment(datum);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
    }

    @Override
    public void onEdit(int viewType, int position) {

    }

    @Override
    public void onDelete(int viewType, int position) {

    }

    private void init() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            mBinding.tvName.setText(user.getDisplayName() == null ? "" : user.getDisplayName());
            mBinding.tvEmail.setText(user.getEmail() == null ? "" : user.getEmail());
        }

        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                LoginManager.getInstance().logOut();
                getBaseActivity().activityToActivity(AuthenticationActivity.class);

                getBaseActivity().finish();

            }
        });

//      Adaptor initializing and set to recycler
        mAdapter = new HotelsAdaptor<Datum>(getActivity(), datams, this);

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    private void observeViewModels() {
        mViewModel.getHotelsData().observe(getViewLifecycleOwner(), mHotelsDataObserver);
    }

    private void getData() {
        mViewModel.getHotels();
    }

//    Observer to get hotel details response
    private
    Observer<? super AppResource<List<Datum>>> mHotelsDataObserver = new Observer<AppResource<List<Datum>>>() {
        @Override
        public void onChanged(AppResource<List<Datum>> response) {
            dismissWaiting();
            if (response == null)
                return;

            switch (response.status) {
                case LOADING:
                    showWaiting();
                    break;
                case SUCCESS:
                    mAdapter.setItems(response.data);
                    break;
                case ERROR:
                    if (response.code == -1)
                        showMessage(getString(R.string.no_internet));
                    else
                        showMessage(response.message);
                    break;
            }
        }
    };

    public interface FragmentEventListener {
        void onMenuDetailFragment(Datum datum);
    }
}