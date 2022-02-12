package com.elegantmedia.test.ui.hotels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elegantmedia.test.AppConstant;
import com.elegantmedia.test.model.AppResource;
import com.elegantmedia.test.model.Datum;
import com.elegantmedia.test.model.Hotel;
import com.elegantmedia.test.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HotelsViewModel extends ViewModel {
    private static final String TAG = "PokemonViewModel";

    private Repository repository;
    private CompositeDisposable disposable;
    private MutableLiveData<AppResource<List<Datum>>> mutableLiveHotels = new MutableLiveData<>();

    @Inject
    public HotelsViewModel(Repository repository) {
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    public MutableLiveData<AppResource<List<Datum>>> getHotelsData() {
        return mutableLiveHotels;
    }

    public void getHotels(){
        Log.d(TAG, "getHotels: ");
        mutableLiveHotels.setValue(AppResource.loading(null));
        disposable.add(repository.getHotels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Hotel>() {
                    @Override
                    public void onSuccess(Hotel data) {

                        if(data != null && data.getData() != null){

                            mutableLiveHotels.postValue(AppResource.success(AppConstant.SUCCESS, data.getData()));
                        }else{

                            mutableLiveHotels.postValue(AppResource.error(AppConstant.ERROR_NO_DATA));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        mutableLiveHotels.postValue(AppResource.error(e.getMessage()));
                    }
                }));
    }
}