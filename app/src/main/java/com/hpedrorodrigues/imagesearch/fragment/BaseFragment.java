package com.hpedrorodrigues.imagesearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.answers.ContentViewEvent;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.general.ISAnswer;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {

    private Toolbar toolbar;
    private ISComponent component;
    private Tracker tracker;

    @Inject
    protected ISAnswer answer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseActivity activity = (BaseActivity) getActivity();

        toolbar = activity.getToolbar();
        component = activity.getComponent();
        tracker = activity.getTracker();

        component.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        tracker.setScreenName(getScreenName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        answer.instance()
                .logContentView(new ContentViewEvent().putContentId("Fragment Screen:" + getScreenName()));
    }

    protected abstract void setUpPresenter();

    protected abstract void inject();

    protected abstract String getScreenName();
}