package com.iessaladillo.pablo.pr09_navigation_pabloromeromontero.ui.fragment;

import android.os.Bundle;

import com.iessaladillo.pablo.pr09_navigation_pabloromeromontero.R;

import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceFragmentCompat;

public class PreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String key) {
        setPreferencesFromResource(R.xml.preference_settings, key);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbarSettin);
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
    }


}
