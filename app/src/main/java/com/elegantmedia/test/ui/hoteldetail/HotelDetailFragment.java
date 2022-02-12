package com.elegantmedia.test.ui.hoteldetail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegantmedia.test.R;
import com.elegantmedia.test.base.BaseFragment;
import com.elegantmedia.test.databinding.FragmentHotelDetailBinding;
import com.elegantmedia.test.model.Datum;
import com.elegantmedia.test.utils.Helper;

import org.jetbrains.annotations.NotNull;

public class HotelDetailFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private FragmentHotelDetailBinding mBinding;
    private FragmentEventListener mListener;

    private Datum mDatum;

    public HotelDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HotelDetailFragment.
     */
    public static HotelDetailFragment newInstance(Datum param1) {
        HotelDetailFragment fragment = new HotelDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDatum = getArguments().getParcelable(ARG_PARAM1);
            mListener.onHotelAdded(mDatum);
        }
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
        mListener = (FragmentEventListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHotelDetailBinding.bind(inflater.inflate(R.layout.fragment_hotel_detail,
                container, false));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        getBaseActivity().setTitle(getString(R.string.title_details));
    }

    private void init() {
        if(mDatum == null)
            showMessage(getString(R.string.data_null));

        mBinding.tvTitle.setText(mDatum.getTitle());
        mBinding.tvDescription.setText(mDatum.getDescription());
        Helper.setImage(mDatum.getImage().getMedium(), mBinding.ivProfile);
    }

    public interface FragmentEventListener {
        void onHotelAdded(Datum datum);
    }
}