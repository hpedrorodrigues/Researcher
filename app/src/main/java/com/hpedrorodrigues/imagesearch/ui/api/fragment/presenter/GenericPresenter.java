package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.view.View;

import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;
import com.hpedrorodrigues.imagesearch.util.rx.Rx;

import java.util.List;

import timber.log.Timber;

public class GenericPresenter extends BasePresenter<GenericFragment> {

    private final GenericView view;

    private final Api api;

    public GenericPresenter(GenericFragment fragment, Navigator navigator, Api api) {
        super(fragment, navigator);
        this.api = api;
        this.view = new GenericView(fragment);

        getActivity().getComponent().inject(view);
    }

    @Override
    public void onViewCreated(View view) {
        this.view.onView(view);

        setUp();
    }

    public void setUp() {
        genericService
                .search(api, "car", 1, 15, false)
                .compose(Rx.applySchedulers())
                .subscribe(
                        data -> {
                            List<Image> images = genericService.parse(api, data);
                            view.setContentFromGridView(images);
                            Timber.i("Images loaded %s", images);
                        },
                        error -> Timber.e(error, "Error")
                );
    }
}