package com.hpedrorodrigues.imagesearch.network.api.duckduckgo;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DuckDuckGoApi {

    String ENDPOINT = "https://api.duckduckgo.com/";

    @GET("/i.js")
    Observable<Map> search(@Query("next") Integer next,
                           @Query("s") Integer s,
                           @Query("q") String q);
}