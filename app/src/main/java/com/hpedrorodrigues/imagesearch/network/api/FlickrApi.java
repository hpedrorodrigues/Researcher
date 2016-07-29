package com.hpedrorodrigues.imagesearch.network.api;

import com.hpedrorodrigues.imagesearch.network.services.flickr.FlickrCallback;
import com.hpedrorodrigues.imagesearch.network.services.flickr.FlickrMethod;
import com.hpedrorodrigues.imagesearch.network.services.flickr.FlickrOutputFormat;
import com.hpedrorodrigues.imagesearch.network.services.flickr.FlickrService;

import java.util.Map;

import retrofit2.Call;
import rx.Observable;

class FlickrApi extends BaseApi {

    public FlickrApi() {
    }

    @Override
    public Api getApi() {
        return Api.FLICKR;
    }

    @Override
    protected Observable<Map> search(final String text, final Integer page,
                                     final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getFlickrApi()
                .search(
                        FlickrMethod.SEARCH.getId(),
                        FlickrService.API_KEY,
                        FlickrOutputFormat.JSON.getValue(),
                        FlickrCallback.OFF.getValue(),
                        text,
                        page,
                        perPage
                );
    }

    @Override
    protected Call<Map> callSearch(final String text, final Integer page,
                                   final Integer perPage, final Boolean safeSearch) {
        return serviceFactory
                .getFlickrApi()
                .callSearch(
                        FlickrMethod.SEARCH.getId(),
                        FlickrService.API_KEY,
                        FlickrOutputFormat.JSON.getValue(),
                        FlickrCallback.OFF.getValue(),
                        text,
                        page,
                        perPage
                );
    }
}