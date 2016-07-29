package com.hpedrorodrigues.imagesearch.network;

import com.hpedrorodrigues.imagesearch.network.api.bing.BingParameter;
import com.hpedrorodrigues.imagesearch.network.api.cse.CSEApi;
import com.hpedrorodrigues.imagesearch.network.api.cse.CSEParameter;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrApi;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrMethod;
import com.hpedrorodrigues.imagesearch.network.api.flickr.FlickrOutputFormat;
import com.hpedrorodrigues.imagesearch.network.api.imgur.ImgurImageType;
import com.hpedrorodrigues.imagesearch.network.api.street_view.StreetViewApi;
import com.hpedrorodrigues.imagesearch.network.api.street_view.StreetViewImageDetail;
import com.hpedrorodrigues.imagesearch.network.api.Api;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

public class ImageApi {

    @Inject
    public APIFactory apiFactory;

    @Inject
    public StreetViewApi streetViewApi;

    @Inject
    public ImageApi() {
    }

    public String getImageUrl(StreetViewImageDetail imageDetail) {
        return streetViewApi.getImageUrl(imageDetail);
    }

    public Observable<Map> search(Api api, String text, Integer page,
                                  Integer perPage, Boolean safeSearch) {
        switch (api) {

            case FLICKR:
                return flickrSearch(text, page, perPage, safeSearch);

            case CSE:
                return cseSearch(text, page, perPage, safeSearch);

            case IMGUR:
                return imgurSearch(text, page, perPage, safeSearch);

            case DUCK_DUCK_GO:
                return duckDuckGoSearch(text, page, perPage, safeSearch);

            case BING:
                return bingSearch(text, page, perPage, safeSearch);

            default:
                return null;
        }
    }

    private Observable<Map> flickrSearch(String text, Integer page,
                                         Integer perPage, Boolean safeSearch) {
        return apiFactory.getFlickrApi()
                .search(
                        FlickrMethod.SEARCH.getId(),
                        FlickrApi.API_KEY,
                        FlickrOutputFormat.JSON.getValue(),
                        text,
                        perPage,
                        page,
                        1
                );
    }

    private Observable<Map> cseSearch(String text, Integer page,
                                      Integer perPage, Boolean safeSearch) {
        return apiFactory.getCseApi()
                .search(
                        CSEApi.API_KEY,
                        CSEParameter.RSZ,
                        page,
                        perPage,
                        CSEParameter.SOURCE,
                        CSEParameter.GSS,
                        CSEParameter.SIG,
                        CSEParameter.SEARCH_TYPE,
                        CSEParameter.CX,
                        CSEParameter.GOOGLE_HOST,
                        text
                );
    }

    private Observable<Map> imgurSearch(String text, Integer page,
                                        Integer perPage, Boolean safeSearch) {
        return apiFactory.getImgurApi()
                .search(
                        ImgurImageType.JPG.getValue(),
                        text,
                        page,
                        perPage
                );
    }

    private Observable<Map> duckDuckGoSearch(String text, Integer page,
                                             Integer perPage, Boolean safeSearch) {

        return apiFactory.getDuckDuckGoApi().search(page, perPage, text);
    }

    private Observable<Map> bingSearch(String text, Integer page,
                                       Integer perPage, Boolean safeSearch) {
        return apiFactory.getBingApi()
                .search(text, perPage, page, BingParameter.SAFE_SEARCH);
    }
}