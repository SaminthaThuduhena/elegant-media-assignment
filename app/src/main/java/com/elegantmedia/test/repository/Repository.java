package com.elegantmedia.test.repository;


import com.elegantmedia.test.model.Hotel;
import com.elegantmedia.test.network.HotelApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class Repository {

    private HotelApiService apiService;

    @Inject
    public Repository(HotelApiService apiService) {
        this.apiService = apiService;
    }

    public Single<Hotel> getHotels(){
        return apiService.getHotels();
    }
}
