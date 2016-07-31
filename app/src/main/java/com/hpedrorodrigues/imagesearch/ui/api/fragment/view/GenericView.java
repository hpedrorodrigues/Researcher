package com.hpedrorodrigues.imagesearch.ui.api.fragment.view;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.etsy.android.grid.StaggeredGridView;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.ui.adapter.ImageAdapter;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import javax.inject.Inject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenericView extends BaseView<GenericFragment> {

    @Inject
    public ImageAdapter imagesAdapter;

    private StaggeredGridView gridView;

    private AVLoadingIndicatorView loadingView;

    public GenericView(GenericFragment fragment) {
        super(fragment);
    }

    @Override
    public void onView(View view) {
        gridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
        loadingView = (AVLoadingIndicatorView) view.findViewById(R.id.loadingIndicatorView);
    }

    public void showProgress() {
        loadingView.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
    }

    public void hideProgress() {
        loadingView.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
    }

    public void setUpImageAdapter() {
        imagesAdapter.setListener((item, image) -> {
        });
    }

    public void setContentFromGridView(List<Image> images) {
        imagesAdapter.setContent(images);
        gridView.setAdapter(imagesAdapter);
    }

    public void clearImageAdapter() {
        imagesAdapter.clear();
    }

    public SearchView getSearchView(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(context.getString(R.string.search_hint));
        searchView.setFocusable(true);
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();

        return searchView;
    }
}