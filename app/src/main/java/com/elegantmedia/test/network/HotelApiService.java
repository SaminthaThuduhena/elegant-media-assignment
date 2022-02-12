package com.elegantmedia.test.network;

import com.elegantmedia.test.model.Hotel;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface HotelApiService {

    @GET("s/6nt7fkdt7ck0lue/hotels.json")
    Single<Hotel> getHotels();
}
