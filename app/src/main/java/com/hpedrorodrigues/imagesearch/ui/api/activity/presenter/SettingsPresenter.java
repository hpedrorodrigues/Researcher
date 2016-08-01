package com.hpedrorodrigues.imagesearch.ui.api.activity.presenter;

import android.os.Bundle;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.constant.PreferenceKey;
import com.hpedrorodrigues.imagesearch.ui.activity.AboutActivity;
import com.hpedrorodrigues.imagesearch.ui.activity.SettingsActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.view.SettingsView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;

public class SettingsPresenter extends BasePresenter<SettingsActivity> {

    private final SettingsView view;

    public SettingsPresenter(SettingsActivity activity, Navigator navigator) {
        super(activity, navigator);
        view = new SettingsView(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        view.onView();

        setUpListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigator.toActivityParent();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpListeners() {
        view.getCloseAppContainer().setOnClickListener((v) -> {
            boolean isChecked = !view.getToggleCloseTheApp().isChecked();
            view.getToggleCloseTheApp().setChecked(isChecked);

            preferences.putBoolean(PreferenceKey.ASK_TO_EXIT, ISConstant.DEFAULT_ASK_TO_EXIT);
        });

        view.getToggleCloseTheApp().setOnCheckedChangeListener((compoundButton, isChecked) ->
                preferences.putBoolean(PreferenceKey.ASK_TO_EXIT, ISConstant.DEFAULT_ASK_TO_EXIT));

        view.getKeepScreenOnContainer().setOnClickListener((v) -> {
            boolean isChecked = !view.getToggleKeepScreenOn().isChecked();
            view.getToggleKeepScreenOn().setChecked(isChecked);

            preferences.putBoolean(PreferenceKey.KEEP_SCREEN_ON, ISConstant.DEFAULT_KEEP_SCREEN_ON);
        });

        view.getToggleKeepScreenOn().setOnCheckedChangeListener((compoundButton, b) ->
                preferences.putBoolean(PreferenceKey.KEEP_SCREEN_ON, ISConstant.DEFAULT_KEEP_SCREEN_ON));

        view.getAboutTheApp().setOnClickListener((v) -> navigator.toActivityScreen(AboutActivity.class));
    }
}